package com.aitribe.lms.service.serviceImpl;

import com.aitribe.lms.Util.IdGenerator;
import com.aitribe.lms.Util.ValidationUtil;
import com.aitribe.lms.entity.Book;
import com.aitribe.lms.entity.Loan;
import com.aitribe.lms.entity.Patron;
import com.aitribe.lms.enums.BookStatus;
import com.aitribe.lms.repository.BookRepository;
import com.aitribe.lms.repository.LoanRepository;
import com.aitribe.lms.repository.PatronRepository;
import com.aitribe.lms.service.LendingService;
import com.aitribe.lms.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class LendingServiceImpl implements LendingService {

    private static final Logger log = LoggerFactory.getLogger(LendingServiceImpl.class);

    private static final int MAX_LOAN_COUNT = 3;

    private final LoanRepository loanRepo;
    private final BookRepository bookRepo;
    private final PatronRepository patronRepo;
    private final ReservationService reservationService;

    public LendingServiceImpl(LoanRepository loanRepo, BookRepository bookRepo, PatronRepository patronRepo, ReservationService reservationService, String defaultBranchId) {
        this.loanRepo = loanRepo;
        this.bookRepo = bookRepo;
        this.patronRepo = patronRepo;
        this.reservationService = reservationService;

    }


    @Override
    public Loan checkoutBook(String isbn, String patronId) {

        Book book = bookRepo.findByIsbn(isbn).orElseThrow(() -> new RuntimeException("Book with ISBN not exists :! " + isbn));

        ValidationUtil.validate(book.status().equals(BookStatus.AVAILABLE), "Can not checkout.! Book is not available :! " + isbn);

        Patron patron = patronRepo.findByPatronId(patronId).orElseThrow(() -> new RuntimeException("Patron with Id not exists :! " + patronId));

        //get active Loans

        long activeLoanCount = loanRepo.findOpenLoansByIsbn(patronId).stream().filter(Loan::isOpen).count();

        ValidationUtil.validate(activeLoanCount < MAX_LOAN_COUNT, "Can not checkout.! Patron has reached maximum loan limit :! " + patronId);

        book.setStatus(BookStatus.BORROWED);
        bookRepo.save(book);

        String loanId = IdGenerator.generateLoanId();
        Loan loan = new Loan(loanId, isbn, patronId, LocalDate.now());

        loanRepo.save(loan);
        log.info("Book checked out successfully :! {}", loan);

        patron.addLoanToHistory(loanId);
        patronRepo.save(patron);


        // if this was a reserved book picked up by the right patron .

        reservationService.markReservationFulfilledIfApplicable(isbn, patronId);

        log.info("Checkout successful : loanId={}, isbn={}, patronId={}, branchID={}", loanId, isbn, patronId, book.branchId());


        return loan;


    }

    @Override
    public Loan returnBook(String isbn) {

        //GetBook
        Book book = bookRepo.findByIsbn(isbn).orElseThrow(() -> new RuntimeException("Book with ISBN not exists :! " + isbn));

        //Validate is Book is Borrowed
        ValidationUtil.validate(book.status().equals(BookStatus.BORROWED), "Can not return.! Book is not borrowed :! " + isbn);

        //list OpenLoans
        List<Loan> openLoans = loanRepo.findOpenLoansByIsbn(isbn);

        ValidationUtil.validate(!openLoans.isEmpty(), "Can not return.! No OpenLoans found.! Book is not borrowed :! " + isbn);

        Loan loan = openLoans.get(0);
        loan.close();

        loanRepo.save(loan);

        //Reservation Seervice will decide whether book becomes AVAIlABLE or RESERVED
        reservationService.processReturnedBook(isbn);

        book.setStatus(BookStatus.AVAILABLE);
        bookRepo.save(book);

        log.info("Book returned successfully :! {}", loan);

        return loan;

    }

    @Override
    public Loan checkoutBook(String isbn, String patronId, String branchId) {

        //get Book

        Book book = bookRepo.findByIsbn(isbn).orElseThrow(() -> new RuntimeException("Book with ISBN not exists :! " + isbn));

        //get Patron

        Patron patron = patronRepo.findByPatronId(patronId).orElseThrow(() -> new RuntimeException("Patron with Id not exists :! " + patronId));

        ValidationUtil.validate(book.branchId().equals(branchId), "Can not checkout.! Book is not available in this branch :! " + branchId);

        ValidationUtil.validate(book.status() == BookStatus.AVAILABLE, "Book is already Borrowed.!");

        long activeLoanCount = loanRepo.findOpenLoansByIsbn(patronId).stream().filter(Loan::isOpen).count();

        //Validate count
        ValidationUtil.validate(activeLoanCount < MAX_LOAN_COUNT, "Can not checkout.! Patron has reached maximum loan limit :! " + patronId);

        book.setStatus(BookStatus.BORROWED);
        bookRepo.save(book);

        String loanId = IdGenerator.generateLoanId();
        Loan loan = new Loan(loanId, isbn, patronId, LocalDate.now());
        loanRepo.save(loan);
        log.info("Book checked out successfully :! {}", loan);

        patron.addLoanToHistory(loanId);
        patronRepo.save(patron);

        log.info("Patron loan history updated successfully :! {}", patron);
        return loan;

    }

    @Override
    public List<Loan> getLoansByPatronId(String patronId) {

        ValidationUtil.validate(patronRepo.existsByPatronId(patronId), "Patron not found" + patronId);

        return loanRepo.findLoansByPatronId(patronId);
    }

    @Override
    public List<Loan> listAllLoans() {
        return loanRepo.findAll();
    }
}

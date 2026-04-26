package com.aitribe.lms.service.serviceImpl;

import com.aitribe.lms.Util.IdGenerator;
import com.aitribe.lms.Util.ValidationUtil;
import com.aitribe.lms.entity.Book;
import com.aitribe.lms.entity.Patron;
import com.aitribe.lms.entity.Reservation;
import com.aitribe.lms.enums.BookStatus;
import com.aitribe.lms.enums.ReservationStatus;
import com.aitribe.lms.repository.BookRepository;
import com.aitribe.lms.repository.PatronRepository;
import com.aitribe.lms.repository.ReservationRepository;
import com.aitribe.lms.service.ReservationService;
import com.aitribe.lms.service.observer.ReservationObserver;
import com.aitribe.lms.service.observer.ReservationSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationServiceImpl implements ReservationService, ReservationSubject {

    private static final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final ReservationRepository reservationRepo;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final List<ReservationObserver> observers = new ArrayList<>();

    public ReservationServiceImpl(ReservationRepository reservationRepo, BookRepository bookRepository, PatronRepository patronRepository) {
        this.reservationRepo = reservationRepo;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }


    @Override
    public Reservation reserveBook(String isbn, String patronId) {

        Book book = bookRepository.findByIsbn(isbn).orElseThrow(() -> new IllegalArgumentException("Book not found with ISBN :" + isbn));

        Patron patron = patronRepository.findByPatronId(patronId).orElseThrow(() -> new IllegalArgumentException("Patron not found with ID :" + patronId));


        ValidationUtil.validate(book.status() == BookStatus.BORROWED || book.status() == BookStatus.RESERVED, "Book is not available for reservation");

        ValidationUtil.validate(reservationRepo.findFirstByIsbnAndPatronId(isbn, patronId).isEmpty(), "Patron already has an active  reservation for this book");

        Reservation reservation = new Reservation(IdGenerator.generateReservationId(), isbn, patronId, LocalDateTime.now());

        reservationRepo.save(reservation);
        log.info("Reservation created : {}", reservation);


        return reservation;
    }

    @Override
    public void cancelReservation(String reservationId) {

        Reservation reservation = reservationRepo.findByReservationId(reservationId).orElseThrow(() -> new IllegalArgumentException("Reservation not found with ID :" + reservationId));

        ValidationUtil.validate(reservation.isActive(), "Reservation is not active");

        reservation.setStatus(ReservationStatus.CANCELLED);

        reservationRepo.save(reservation);
        log.info("Reservation cancelled : {}", reservation);


    }


    @Override
    public List<Reservation> getReservationByPatronId(String patronId) {

        ValidationUtil.validate(patronRepository.existsByPatronId(patronId), "Patron not found with ID :" + patronId);

        return reservationRepo.findByPatronId(patronId);
    }

    @Override
    public List<Reservation> listAllReservation() {
        return reservationRepo.findAll();
    }

    @Override
    public void processReturnedBook(String isbn) {

        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ISBN :" + isbn));

        var nextReservationOpt = reservationRepo.findFirstWaitingByIsbn(isbn);

        if (nextReservationOpt.isPresent()) {

            Reservation nextReservation = nextReservationOpt.get();
            Patron patron = patronRepository.findByPatronId(nextReservation.patronId())
                    .orElseThrow(() -> new IllegalArgumentException("Patron not found with ID :" + nextReservation.patronId()));

            nextReservation.setStatus(ReservationStatus.NOTIFIED);
            reservationRepo.save(nextReservation);

            book.setStatus(BookStatus.RESERVED);
            bookRepository.save(book);

            //notify Observer
            notifyObserver(nextReservation, book, patron);

            log.info("Book {} is now available for patron {}", book, patron);


        } else {

            book.setStatus(BookStatus.AVAILABLE);
            bookRepository.save(book);
            log.info("Book {} is now available", book);
        }


    }


    @Override
    public boolean isReservedForPatron(String isbn, String patronId) {
        return reservationRepo.findFirstNotifiedByIsbn(isbn)
                .map(reservation -> reservation.patronId().equals(patronId))
                .orElse(false);
    }

    @Override
    public void markReservationFulfilledIfApplicable(String isbn, String patronId) {

        var notifiedOpt = reservationRepo.findFirstNotifiedByIsbn(isbn);

        if (notifiedOpt.isPresent()) {
            Reservation reservation = notifiedOpt.get();

            ValidationUtil.validate(reservation.patronId().equals(patronId),
                    "This reserved book is assigned to another patron.");

            reservation.setStatus(ReservationStatus.FULFILLED);
            reservationRepo.save(reservation);

            log.info("Reservation fulfilled: {}", reservation);
        }

    }

    @Override
    public void addObserver(ReservationObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ReservationObserver observer) {

        observers.remove(observer);
    }

    private void notifyObserver(Reservation reservation, Book book, Patron patron) {

        for (ReservationObserver observer : observers) {
            observer.onBookAvailable(reservation, book, patron);
        }

    }

}

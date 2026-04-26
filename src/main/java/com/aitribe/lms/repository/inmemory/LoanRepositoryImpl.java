package com.aitribe.lms.repository.inmemory;

import com.aitribe.lms.entity.Loan;
import com.aitribe.lms.repository.LoanRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LoanRepositoryImpl implements LoanRepository {

    private final Map<String, Loan> loans = new HashMap<>();

    @Override
    public List<Loan> findAll() {

        return loans.values().stream().toList();

    }

    @Override
    public void save(Loan loan) {

        loans.put(loan.loanId(), loan);

    }

    @Override
    public Optional<Loan> findByLoanId(String loanId) {
        return Optional.ofNullable(loans.get(loanId));
    }

    @Override
    public List<Loan> findOpenLoansByIsbn(String isbn) {
        return loans.values().stream()
                .filter(loan -> loan.isbn().equals(isbn) && !loan.isOpen())
                .toList();
    }

    @Override
    public List<Loan> findLoansByPatronId(String patronId) {
        return loans.values().stream()
                .filter(loan -> loan.patronId().equals(patronId))
                .toList();
    }
}

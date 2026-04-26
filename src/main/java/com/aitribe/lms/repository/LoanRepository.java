package com.aitribe.lms.repository;

import com.aitribe.lms.entity.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepository {

    void save(Loan loan);

    Optional<Loan> findByLoanId(String loanId);

    List<Loan> findAll();

    List<Loan> findOpenLoansByIsbn(String isbn);

    List<Loan> findLoansByPatronId(String patronId);
}

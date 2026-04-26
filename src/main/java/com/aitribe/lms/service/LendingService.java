package com.aitribe.lms.service;

import com.aitribe.lms.entity.Loan;

import java.util.List;

public interface LendingService {

    Loan checkoutBook(String isbn, String patronId);

    Loan returnBook(String isbn);

    // Branch aware checkoutBook
    Loan checkoutBook(String isbn, String patronId, String branchId);

    List<Loan> getLoansByPatronId(String patronId);

    List<Loan> listAllLoans();
}

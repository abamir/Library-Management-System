package com.aitribe.lms.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {

    private final String loanId;
    private final String isbn;
    private final String patronId;
    private final LocalDate checkoutDate;
    private LocalDate returnDate; // null => Still Borrowed


    public Loan(String loanId, String isbn, String patronId, LocalDate checkoutDate) {
        this.loanId = Objects.requireNonNull(loanId, "LoanId cannot be null").trim();
        this.isbn = Objects.requireNonNull(isbn, "isbn cannot be null").trim();
        this.patronId = Objects.requireNonNull(patronId, "patronId cannot be null").trim();
        this.checkoutDate = Objects.requireNonNull(checkoutDate, "checkoutDate cannot be null");
    }

    public String isbn() {
        return isbn;
    }

    public LocalDate checkoutDate() {
        return checkoutDate;
    }

    public String loanId() {
        return loanId;
    }

    public String patronId() {
        return patronId;
    }

    public LocalDate returnDate() {
        return returnDate;
    }

    public boolean isOpen() {
        return returnDate == null;
    }

    public void close() {
        returnDate = Objects.requireNonNull(returnDate, "Return date cannot be null");
    }

    //toString
    @Override
    public String toString() {
        return "Loan[loanId=%s, isbn=%s, patronId=%s, checkoutDate=%s, returnDate=%s]"
                .formatted(loanId, isbn, patronId, checkoutDate, returnDate);
    }
}



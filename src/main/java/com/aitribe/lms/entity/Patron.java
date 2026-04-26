package com.aitribe.lms.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Patron {

    private final String patronId;
    private final List<String> loanHistory = new ArrayList<>();
    private String name;
    private String email;
    private String phone;

    //Constructor
    public Patron(String patronId, String name, String email, String phone) {

        this.patronId = Objects.requireNonNull(patronId, "PatronId cannot be null").trim();
        this.name = Objects.requireNonNull(name, "name cannot be null").trim();
        this.email = Objects.requireNonNull(email, "email cannot be null").trim();
        this.phone = Objects.requireNonNull(phone, "phone cannot be null").trim();
    }

    // Getters

    public String patronId() {
        return patronId;
    }

    public String name() {
        return name;
    }

    public String email() {

        return email;
    }

    public String phone() {
        return phone;
    }

    //Setters


    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getLoanHistory() {
        return List.copyOf(loanHistory);
    }

    public void addLoanToHistory(String isbn) {
        loanHistory.add(isbn);
    }

    //toString
    @Override
    public String toString() {
        return "Patron[PatronId=%s, name=%s, email=%s, phone=%s]"
                .formatted(patronId, name, email, phone);
    }

}

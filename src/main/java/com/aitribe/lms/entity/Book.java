package com.aitribe.lms.entity;

import com.aitribe.lms.enums.BookStatus;

import java.util.Objects;

public class Book {

    // Fields

    private final String isbn;
    private String title;
    private String author;
    private int publishingYear;
    private BookStatus status;

    private String branchId;

    //Constructor
    public Book(String isbn, String title, String author, int publishingYear, String branchId) {

        this.isbn = Objects.requireNonNull(isbn, "ISBN cannot be null").trim();
        this.title = Objects.requireNonNull(title, "title Cannot be null").trim();
        this.author = Objects.requireNonNull(author, "author cannot be null").trim();
        this.publishingYear = publishingYear;
        this.status = BookStatus.AVAILABLE;
        this.branchId = Objects.requireNonNull(branchId, "BranchId cannot be null").trim();

    }

    //Getters and Setters


    public String author() {
        return author;
    }

    public String branchId() {
        return branchId;
    }

    public String isbn() {
        return isbn;
    }

    public int publishingYear() {
        return publishingYear;
    }

    public BookStatus status() {
        return status;
    }

    public String title() {
        return title;
    }

    //Setters

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //toString
    @Override
    public String toString() {
        //return Book[isbn=" + isbn + ", title=" + title + ", author=" + author + ", publishingYear=" + publishingYear + ", status=" + status + "]";

        return "Book[isbn=%s, title=%s, author=%s, publishingYear=%s, status=%s]"
                .formatted(isbn, title, author, publishingYear, status);


    }
}

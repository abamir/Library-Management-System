package com.aitribe.lms.service;

import com.aitribe.lms.entity.Book;

import java.util.List;

public interface CatalogService {

    void addBook(Book book);

    void updateBook(String isbn, String title, String author, int year);

    void removeBook(String isbn);

    List<Book> searchByTitle(String title);

    List<Book> searchByAuthor(String author);

    Book searchByIsbn(String isbn);

    List<Book> listAllBooks();


}

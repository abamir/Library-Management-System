package com.aitribe.lms.service.serviceImpl;

import com.aitribe.lms.Util.ValidationUtil;
import com.aitribe.lms.entity.Book;
import com.aitribe.lms.repository.BookRepository;
import com.aitribe.lms.service.CatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CatalogServiceImpl implements CatalogService {

    private static final Logger log = LoggerFactory.getLogger(CatalogServiceImpl.class);

    private final BookRepository bookRepo;

    public CatalogServiceImpl(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public void addBook(Book book) {

        ValidationUtil.validate(!bookRepo.existsByIsbn(book.isbn()),
                "Book With ISBN already exists :! " + book.isbn());

        bookRepo.save(book);
        log.info("Book added successfully :! {}", book);

    }

    @Override
    public void updateBook(String isbn, String title, String author, int year) {

        ValidationUtil.validate(bookRepo.existsByIsbn(isbn),
                "Book With ISBN not exists :! " + isbn);

        Optional<Book> existing = bookRepo.findByIsbn(isbn);

        existing.get().setAuthor(author);
        existing.get().setTitle(title);
        existing.get().setPublishingYear(year);

        bookRepo.save(existing.get());
        log.info("Book updated successfully :! {}", existing.get());
    }

    @Override
    public void removeBook(String isbn) {

        ValidationUtil.validate(bookRepo.existsByIsbn(isbn),
                "Book With ISBN not exists :! " + isbn);

        bookRepo.deleteByIsbn(isbn);
        log.info("Book deleted successfully :! {}", isbn);

    }

    @Override
    public List<Book> searchByTitle(String title) {

        String titleQuery = title == null ? "" : title.trim().toLowerCase();


        return bookRepo.findAll()
                .stream()
                .filter(book -> book.title().toLowerCase().contains(titleQuery))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> searchByAuthor(String author) {

        String authorQuery = author == null ? "" : author.trim().toLowerCase();


        return bookRepo.findAll()
                .stream()
                .filter(book -> book.author().toLowerCase().contains(authorQuery))
                .collect(Collectors.toList());
    }

    @Override
    public Book searchByIsbn(String isbn) {


        Optional<Book> bookByIsbn = bookRepo.findByIsbn(isbn);
        return bookByIsbn.get();
    }

    @Override
    public List<Book> listAllBooks() {

        return bookRepo.findAll();

    }
}

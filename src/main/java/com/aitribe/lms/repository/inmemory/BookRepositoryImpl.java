package com.aitribe.lms.repository.inmemory;

import com.aitribe.lms.entity.Book;
import com.aitribe.lms.repository.BookRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class BookRepositoryImpl implements BookRepository {

    private final Map<String, Book> bookStore = new HashMap<>();

    @Override
    public void save(Book book) {

        bookStore.put(book.isbn(), book);


    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(bookStore.get(isbn));
    }

    @Override
    public List<Book> findAll() {
        return bookStore.values().stream().toList();
    }

    @Override
    public void deleteByIsbn(String isbn) {


        bookStore.remove(isbn);
    }

    @Override
    public boolean existsByIsbn(String isbn) {

        return bookStore.containsKey(isbn);
    }
}

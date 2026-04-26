package com.aitribe.lms.repository;

import com.aitribe.lms.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    void save(Book book);

    Optional<Book> findByIsbn(String isbn);

    List<Book> findAll();

    void deleteByIsbn(String isbn);

    boolean existsByIsbn(String isbn);


}

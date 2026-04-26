package com.aitribe.lms.service.serviceImpl;

import com.aitribe.lms.entity.Book;
import com.aitribe.lms.enums.BookStatus;
import com.aitribe.lms.repository.BookRepository;
import com.aitribe.lms.service.InventoryService;

import java.util.List;

public class InventoryServiceImpl implements InventoryService {

    private final BookRepository bookRepo;

    public InventoryServiceImpl(BookRepository bookRepo, String defaultBranchId) {
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> listAvailableBooks() {
        return bookRepo.findAll()
                .stream()
                .filter(book -> book.status().equals(BookStatus.AVAILABLE))
                .toList();
    }

    @Override
    public List<Book> listBorrowedBooks() {
        return bookRepo.findAll()
                .stream()
                .filter(book -> book.status().equals(BookStatus.BORROWED))
                .toList();
    }

    @Override
    public List<Book> listReservedBooks() {
        return bookRepo.findAll()
                .stream()
                .filter(book -> book.status().equals(BookStatus.RESERVED))
                .toList();
    }

    @Override
    public List<Book> listAvailableBooksByBranch(String branchId) {
        return bookRepo.findAll()
                .stream()
                .filter(book -> book.branchId().equals(branchId))
                .filter(book -> book.status().equals(BookStatus.AVAILABLE))
                .toList();
    }

    @Override
    public List<Book> listBorrowedBooksByBranch(String branchId) {
        return bookRepo.findAll()
                .stream()
                .filter(book -> book.branchId().equals(branchId))
                .filter(book -> book.status().equals(BookStatus.BORROWED))
                .toList();
    }

    @Override
    public List<Book> listReservedBooksByBranch(String branchId) {
        return bookRepo.findAll()
                .stream()
                .filter(book -> book.branchId().equals(branchId))
                .filter(book -> book.status().equals(BookStatus.RESERVED))
                .toList();
    }
}

package com.aitribe.lms.service;

import com.aitribe.lms.entity.Book;

import java.util.LinkedList;
import java.util.List;

public interface InventoryService {


    List<Book> listAvailableBooks();
    List<Book> listBorrowedBooks();
    List<Book> listReservedBooks();

    List<Book> listAvailableBooksByBranch(String branchId);
    List<Book> listBorrowedBooksByBranch(String branchId);
    List<Book> listReservedBooksByBranch(String branchId);

}

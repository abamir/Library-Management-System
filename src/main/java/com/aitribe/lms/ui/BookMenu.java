package com.aitribe.lms.ui;

import com.aitribe.lms.Util.InputUtil;
import com.aitribe.lms.configurations.AppConfig;
import com.aitribe.lms.entity.Book;

import java.util.List;

public class BookMenu extends AbstractManu {


    public BookMenu(AppContext ctx) {

        super(ctx, "BOOKS MENU");

    }

    @Override
    protected void buildMenu() {
        addCommand("Add Book", this::addBook);
        addCommand("Update Book", this::updateBook);
        addCommand("Remove Book", this::removeBook);
        addCommand("Search Books", this::searchBooks);
        addCommand("List All Books", this::listAllBooks);
    }

    private void addBook() {
        String isbn = InputUtil.readNonEmpty(ctx.scanner(), "ISBN: ");
        String title = InputUtil.readNonEmpty(ctx.scanner(), "Title: ");
        String author = InputUtil.readNonEmpty(ctx.scanner(), "Author: ");
        int year = InputUtil.readInt(ctx.scanner(), "Publication Year: ");

        String branchId = readOptional("Branch ID (press Enter for default " + AppConfig.DEFAULT_BRANCH_ID + "): ");
        if (branchId.isBlank()) {
            branchId = AppConfig.DEFAULT_BRANCH_ID;
        }

        ctx.catalogService().addBook(new Book(isbn, title, author, year, branchId));
        System.out.println("✅ Book added successfully.");
    }

    private void updateBook() {
        String isbn = InputUtil.readNonEmpty(ctx.scanner(), "ISBN to update: ");
        String title = InputUtil.readNonEmpty(ctx.scanner(), "New Title: ");
        String author = InputUtil.readNonEmpty(ctx.scanner(), "New Author: ");
        int year = InputUtil.readInt(ctx.scanner(), "New Publication Year: ");

        ctx.catalogService().updateBook(isbn, title, author, year);
        System.out.println("✅ Book updated successfully.");
    }

    private void removeBook() {
        String isbn = InputUtil.readNonEmpty(ctx.scanner(), "ISBN to remove: ");
        ctx.catalogService().removeBook(isbn);
        System.out.println("✅ Book removed successfully.");
    }

    private void searchBooks() {
        System.out.println("Search by:");
        System.out.println("1) Title");
        System.out.println("2) Author");
        System.out.println("3) ISBN");

        int choice = InputUtil.readChoice(ctx.scanner(), "Choose: ", 1, 3);

        switch (choice) {
            case 1 -> {
                String query = InputUtil.readNonEmpty(ctx.scanner(), "Title contains: ");
                List<Book> books = ctx.catalogService().searchByTitle(query);
                printBooks(books);
            }
            case 2 -> {
                String query = InputUtil.readNonEmpty(ctx.scanner(), "Author contains: ");
                List<Book> books = ctx.catalogService().searchByAuthor(query);
                printBooks(books);
            }
            case 3 -> {
                String isbn = InputUtil.readNonEmpty(ctx.scanner(), "ISBN: ");
                Book book = ctx.catalogService().searchByIsbn(isbn);
                System.out.println(book);
            }
            default -> System.out.println("Invalid search option.");
        }
    }

    private void listAllBooks() {
        printBooks(ctx.catalogService().listAllBooks());
    }

    private void printBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        books.forEach(System.out::println);
    }

}

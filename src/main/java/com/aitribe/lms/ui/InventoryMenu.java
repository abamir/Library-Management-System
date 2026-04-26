package com.aitribe.lms.ui;

import com.aitribe.lms.Util.InputUtil;
import com.aitribe.lms.configurations.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventoryMenu extends AbstractManu {

    private static final Logger log = LoggerFactory.getLogger(InventoryMenu.class);

    public InventoryMenu(AppContext ctx) {
        super(ctx, "INVENTORY MENU");
    }

    @Override
    protected void buildMenu() {
        addCommand("List Available Books (Default Branch)", this::listAvailableDefault);
        addCommand("List Borrowed Books (Default Branch)", this::listBorrowedDefault);
        addCommand("List Reserved Books (Default Branch)", this::listReservedDefault);
        addCommand("List Available Books By Branch", this::listAvailableByBranch);
        addCommand("List Borrowed Books By Branch", this::listBorrowedByBranch);
        addCommand("List Reserved Books By Branch ", this::listReservedBooksByBranch);

    }

    private void listReservedBooksByBranch() {

        String branchId = InputUtil.readNonEmpty(ctx.scanner(), "Branch ID: ");
        var books = ctx.inventoryService().listReservedBooksByBranch(branchId);
        if (books.isEmpty()) {
            System.out.println("No Reserved Book in branch: " + branchId);
            return;
        }
        books.forEach(System.out::println);

    }

    private void listReservedDefault() {

        var books =
                ctx.inventoryService().listReservedBooksByBranch(AppConfig.DEFAULT_BRANCH_ID);
        if (books.isEmpty()) {

            //System.out.println("No Reserved Book in Default Branch");
            log.info("No Reserved Book in Default Branch");
            return;
        }
        books.forEach(System.out::println);

    }

    private void listAvailableDefault() {
        var books = ctx.inventoryService().listAvailableBooks();
        if (books.isEmpty()) {
            System.out.println("No available books in default branch.");
            return;
        }
        books.forEach(System.out::println);
    }

    private void listBorrowedDefault() {
        var books = ctx.inventoryService().listBorrowedBooks();
        if (books.isEmpty()) {
            System.out.println("No borrowed books in default branch.");
            return;
        }
        books.forEach(System.out::println);
    }

    private void listAvailableByBranch() {
        String branchId = InputUtil.readNonEmpty(ctx.scanner(), "Branch ID: ");
        var books = ctx.inventoryService().listAvailableBooksByBranch(branchId);
        if (books.isEmpty()) {
            System.out.println("No available books in branch: " + branchId);
            return;
        }
        books.forEach(System.out::println);
    }

    private void listBorrowedByBranch() {
        String branchId = InputUtil.readNonEmpty(ctx.scanner(), "Branch ID: ");
        var books = ctx.inventoryService().listBorrowedBooksByBranch(branchId);
        if (books.isEmpty()) {
            System.out.println("No borrowed books in branch: " + branchId);
            return;
        }
        books.forEach(System.out::println);
    }
}



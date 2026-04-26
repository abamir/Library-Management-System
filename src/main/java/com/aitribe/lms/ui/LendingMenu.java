package com.aitribe.lms.ui;

import com.aitribe.lms.Util.InputUtil;
import com.aitribe.lms.configurations.AppConfig;
import com.aitribe.lms.entity.Loan;

public class LendingMenu  extends  AbstractManu{

    public LendingMenu(AppContext ctx) {
        super(ctx, "LENDING MENU");
    }

    @Override
    protected void buildMenu() {
        addCommand("Checkout Book (Default Branch)", this::checkoutDefaultBranch);
        addCommand("Checkout Book (Specific Branch)", this::checkoutSpecificBranch);
        addCommand("Return Book", this::returnBook);
        addCommand("List All Loans", this::listAllLoans);
    }

    private void checkoutDefaultBranch() {
        String isbn = InputUtil.readNonEmpty(ctx.scanner(), "ISBN to checkout: ");
        String patronId = InputUtil.readNonEmpty(ctx.scanner(), "Patron ID: ");

        Loan loan = ctx.lendingService().checkoutBook(isbn, patronId, AppConfig.DEFAULT_BRANCH_ID);
        System.out.println("✅ Checkout successful: " + loan);
    }

    private void checkoutSpecificBranch() {
        String isbn = InputUtil.readNonEmpty(ctx.scanner(), "ISBN to checkout: ");
        String patronId = InputUtil.readNonEmpty(ctx.scanner(), "Patron ID: ");
        String branchId = InputUtil.readNonEmpty(ctx.scanner(), "Branch ID: ");

        Loan loan = ctx.lendingService().checkoutBook(isbn, patronId, branchId);
        System.out.println("✅ Checkout successful: " + loan);
    }

    private void returnBook() {
        String isbn = InputUtil.readNonEmpty(ctx.scanner(), "ISBN to return: ");
        Loan loan = ctx.lendingService().returnBook(isbn);
        System.out.println("✅ Return successful: " + loan);
    }

    private void listAllLoans() {
        var loans = ctx.lendingService().listAllLoans();
        if (loans.isEmpty()) {
            System.out.println("No loans found.");
            return;
        }
        loans.forEach(System.out::println);
    }


}

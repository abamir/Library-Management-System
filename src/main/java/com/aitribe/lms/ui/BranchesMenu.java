package com.aitribe.lms.ui;

import com.aitribe.lms.Util.InputUtil;
import com.aitribe.lms.entity.Branch;

public class BranchesMenu extends AbstractManu {


    public BranchesMenu(AppContext ctx) {
        super(ctx, "BRANCHES MENU");
    }

    @Override
    protected void buildMenu() {
        addCommand("Add Branch", this::addBranch);
        addCommand("List All Branches", this::listBranches);
        addCommand("Transfer Book Between Branches", this::transferBook);
    }

    private void addBranch() {
        String branchId = InputUtil.readNonEmpty(ctx.scanner(), "Branch ID: ");
        String name = InputUtil.readNonEmpty(ctx.scanner(), "Branch Name: ");

        ctx.branchService().addBranch(new Branch(branchId, name));
        System.out.println("✅ Branch added successfully.");
    }

    private void listBranches() {
        var branches = ctx.branchService().listAllBranches();
        if (branches.isEmpty()) {
            System.out.println("No branches found.");
            return;
        }
        branches.forEach(System.out::println);
    }

    private void transferBook() {
        String isbn = InputUtil.readNonEmpty(ctx.scanner(), "ISBN to transfer: ");
        String fromBranch = InputUtil.readNonEmpty(ctx.scanner(), "From Branch ID: ");
        String toBranch = InputUtil.readNonEmpty(ctx.scanner(), "To Branch ID: ");

        ctx.branchService().transferBook(isbn, fromBranch, toBranch);
        System.out.println("✅ Book transferred successfully.");
    }
}

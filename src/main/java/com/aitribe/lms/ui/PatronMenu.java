package com.aitribe.lms.ui;

import com.aitribe.lms.Util.InputUtil;
import com.aitribe.lms.entity.Patron;

public class PatronMenu extends AbstractManu {

    public PatronMenu(AppContext ctx) {
        super(ctx, "PATRON MENU");
    }

    @Override
    protected void buildMenu() {
        addCommand("Add Patron", this::addPatron);
        addCommand("Update Patron", this::updatePatron);
        addCommand("List All Patrons", this::listAllPatrons);
        addCommand("View Patron Borrowing History", this::viewPatronHistory);
    }

    private void addPatron() {
        String id = InputUtil.readNonEmpty(ctx.scanner(), "Patron ID: ");
        String name = InputUtil.readNonEmpty(ctx.scanner(), "Name: ");
        String email = InputUtil.readNonEmpty(ctx.scanner(), "Email: ");
        String phone = InputUtil.readNonEmpty(ctx.scanner(), "Phone: ");

        ctx.patronService().addPatron(new Patron(id, name, email, phone));
        System.out.println("✅ Patron added successfully.");
    }

    private void updatePatron() {
        String id = InputUtil.readNonEmpty(ctx.scanner(), "Patron ID to update: ");
        String name = InputUtil.readNonEmpty(ctx.scanner(), "New Name: ");
        String email = InputUtil.readNonEmpty(ctx.scanner(), "New Email: ");
        String phone = InputUtil.readNonEmpty(ctx.scanner(), "New Phone: ");

        ctx.patronService().updatePatron(id, name, email, phone);
        System.out.println("✅ Patron updated successfully.");
    }

    private void listAllPatrons() {
        var patrons = ctx.patronService().ListAllPatrons();
        if (patrons.isEmpty()) {
            System.out.println("No patrons found.");
            return;
        }
        patrons.forEach(System.out::println);
    }


    private void viewPatronHistory() {
        String patronId = InputUtil.readNonEmpty(ctx.scanner(), "Patron ID: ");

        System.out.println(ctx.patronService().getPatron(patronId));

        var loans = ctx.lendingService().getLoansByPatronId(patronId);
        if (loans.isEmpty()) {
            System.out.println("No borrowing history found.");
        } else {
            loans.forEach(System.out::println);
        }
    }
}


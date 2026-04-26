package com.aitribe.lms.ui;

import com.aitribe.lms.Util.InputUtil;

public class ReservationMenu extends AbstractManu {


    public ReservationMenu(AppContext ctx) {
        super(ctx, "RESERVATION MENU");
    }

    @Override
    protected void buildMenu() {

        addCommand("Reserve Book", this::reserveBook);
        addCommand("Cancel Reservation", this::cancelReservation);
        addCommand("View Reservation", this::viewReservationsByPatron);
        addCommand("List Reservations", this::listAllReservations);


    }

    private void reserveBook() {
        String isbn = InputUtil.readNonEmpty(ctx.scanner(), "ISBN to reserve: ");
        String patronId = InputUtil.readNonEmpty(ctx.scanner(), "Patron ID: ");

        var reservation = ctx.reservationService().reserveBook(isbn, patronId);
        System.out.println("✅ Reservation created successfully: " + reservation);
    }

    private void cancelReservation() {
        String reservationId = InputUtil.readNonEmpty(ctx.scanner(), "Reservation ID to cancel: ");
        ctx.reservationService().cancelReservation(reservationId);
        System.out.println("✅ Reservation cancelled successfully.");
    }

    private void viewReservationsByPatron() {
        String patronId = InputUtil.readNonEmpty(ctx.scanner(), "Patron ID: ");
        var reservations = ctx.reservationService().getReservationByPatronId(patronId);

        if (reservations.isEmpty()) {
            System.out.println("No reservations found for patron: " + patronId);
            return;
        }

        reservations.forEach(System.out::println);
    }

    private void listAllReservations() {
        var reservations = ctx.reservationService().listAllReservation();

        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        reservations.forEach(System.out::println);
    }

}

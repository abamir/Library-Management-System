package com.aitribe.lms.service;

import com.aitribe.lms.entity.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation reserveBook(String isbn, String patronId);

    void cancelReservation(String reservationId);

    List<Reservation> getReservationByPatronId(String patronId);

    List<Reservation> listAllReservation();

    /*
     * Cancelled when book is returned.
     * If there is a waiting reservation, notify the next waiting patron and mark the book RESERVED.
     * otherwise mark the book AVAILABLE.
     *
     *  */

    void processReturnedBook(String isbn);

    /*
     * Returns true if the book is reserved for the same patron who is trying to check out.
     *
     * */
    boolean isReservedForPatron(String isbn, String patronId);

    /*
     * IF this patron ius picking up a reserved book, mark that reservation fulfilled.
     *
     * */
    void markReservationFulfilledIfApplicable(String isbn, String patronId);

}

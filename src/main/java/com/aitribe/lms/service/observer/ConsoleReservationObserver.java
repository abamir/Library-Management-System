package com.aitribe.lms.service.observer;

import com.aitribe.lms.entity.Book;
import com.aitribe.lms.entity.Patron;
import com.aitribe.lms.entity.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleReservationObserver implements ReservationObserver {

    private static final Logger log = LoggerFactory.getLogger(ConsoleReservationObserver.class);

    @Override
    public void onBookAvailable(Reservation reservation, Book book, Patron patron) {

        String message = String.format(" 📢 Notification : Book %s (ISBN: %s) is now available for patron %s (%s). Reservation ID : %s",

                book.title(), book.isbn(), patron.name(), reservation.reservationId());
        System.out.println(message);
        log.info(message);

    }
}

package com.aitribe.lms.service.observer;

import com.aitribe.lms.entity.Book;
import com.aitribe.lms.entity.Patron;
import com.aitribe.lms.entity.Reservation;

public interface ReservationObserver {

    public void onBookAvailable(Reservation reservation, Book book, Patron patron);
}

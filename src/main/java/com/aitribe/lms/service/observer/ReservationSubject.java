package com.aitribe.lms.service.observer;

public interface ReservationSubject {

    void addObserver(ReservationObserver observer);

    void removeObserver(ReservationObserver observer);
    //void notifyObservers(Reservation reservation, Book book, Patron patron);
}

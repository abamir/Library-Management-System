package com.aitribe.lms.entity;

import com.aitribe.lms.enums.ReservationStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class Reservation {

    private final String reservationId;
    private final String isbn;

    private final String patronId;

    private final LocalDateTime reservationDateTime;

    private ReservationStatus status;


    public Reservation(String reservationId, String isbn, String patronId, LocalDateTime reservationDateTime) {
        this.reservationId = Objects.requireNonNull(reservationId, "reservationId cannot be null");
        this.isbn = Objects.requireNonNull(isbn, "isbn cannot be null");
        this.patronId = Objects.requireNonNull(patronId, "patronId cannot be null");
        this.reservationDateTime = Objects.requireNonNull(reservationDateTime, "reservationDateTime cannot be null");
        this.status = ReservationStatus.WAITING;

    }

    public String reservationId() {
        return reservationId;
    }

    public String isbn() {
        return isbn;
    }

    public String patronId() {
        return patronId;
    }

    public LocalDateTime reservationDateTime() {
        return reservationDateTime;
    }

    public ReservationStatus status() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public boolean isActive() {
        return status == ReservationStatus.WAITING || status == ReservationStatus.NOTIFIED;
    }

    //toString

    @Override
    public String toString() {
        return "Reservation [reservationId=" + reservationId + ", isbn=" + isbn + ", patronId=" + patronId
                + ", reservationDateTime=" + reservationDateTime + ", status=" + status + "]";
    }
}

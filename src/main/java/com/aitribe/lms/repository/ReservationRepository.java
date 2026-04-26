package com.aitribe.lms.repository;

import com.aitribe.lms.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    void save(Reservation reservation);

    List<Reservation> findAll();

    Optional<Reservation> findByReservationId(String reservationId);

    List<Reservation> findByPatronId(String patronId);


    //findBy ISBN
    List<Reservation> findByIsbn(String isbn);

    Optional<Reservation> findFirstWaitingByIsbn(String isbn);

    Optional<Reservation> findFirstNotifiedByIsbn(String isbn);

    //findFirstByIsbnAndPatronId
    Optional<Reservation> findFirstByIsbnAndPatronId(String isbn, String patronId);


}

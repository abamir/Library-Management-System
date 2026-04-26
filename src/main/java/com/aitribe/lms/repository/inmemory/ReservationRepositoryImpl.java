package com.aitribe.lms.repository.inmemory;

import com.aitribe.lms.entity.Reservation;
import com.aitribe.lms.enums.ReservationStatus;
import com.aitribe.lms.repository.ReservationRepository;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationRepositoryImpl implements ReservationRepository {

    private final Map<String, Reservation> reservationsMap = new HashMap<>();

    @Override
    public void save(Reservation reservation) {

        reservationsMap.put(reservation.reservationId(), reservation);

    }

    @Override
    public List<Reservation> findAll() {
        return reservationsMap.values().stream().toList();
    }

    @Override
    public Optional<Reservation> findByReservationId(String reservationId) {
        return Optional.ofNullable(reservationsMap.get(reservationId));
    }

    @Override
    public List<Reservation> findByPatronId(String patronId) {
        return reservationsMap.values().stream()
                .filter(reservation -> reservation.patronId().equals(patronId))
                .sorted(Comparator.comparing(Reservation::reservationDateTime))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByIsbn(String isbn) {
        return reservationsMap.values().stream()
                .filter(reservation -> reservation.isbn().equals(isbn))
                .sorted(Comparator.comparing(Reservation::reservationDateTime))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Reservation> findFirstWaitingByIsbn(String isbn) {
        return reservationsMap.values().stream()
                .filter(reservation -> reservation.isbn().equals(isbn) && reservation.status() == ReservationStatus.WAITING)
                .findFirst();
    }

    @Override
    public Optional<Reservation> findFirstNotifiedByIsbn(String isbn) {
        return reservationsMap.values().stream()
                .filter(reservation -> reservation.isbn().equals(isbn))
                .filter(reservation -> reservation.status() == ReservationStatus.NOTIFIED)
                .findFirst();
    }

    @Override
    public Optional<Reservation> findFirstByIsbnAndPatronId(String isbn, String patronId) {
        return reservationsMap.values().stream()
                .filter(reservation -> reservation.isbn().equals(isbn))
                .filter(reservation -> reservation.patronId().equals(patronId))
                .filter(Reservation::isActive)
                .findFirst();

    }
}

package com.aitribe.lms.repository;

import com.aitribe.lms.entity.Patron;

import java.util.List;
import java.util.Optional;

public interface PatronRepository {

    void save(Patron patron);

    Optional<Patron> findByPatronId(String patronId);

    List<Patron> findAll();

    //void deleteByPatronId(String patronId);

    boolean existsByPatronId(String patronId);
}
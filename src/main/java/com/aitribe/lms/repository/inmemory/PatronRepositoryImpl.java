package com.aitribe.lms.repository.inmemory;

import com.aitribe.lms.entity.Patron;
import com.aitribe.lms.repository.PatronRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PatronRepositoryImpl implements PatronRepository {

    private final Map<String, Patron> patrons = new HashMap<>();

    @Override
    public void save(Patron patron) {

        patrons.put(patron.patronId(), patron);

    }

    @Override
    public Optional<Patron> findByPatronId(String patronId) {
        return Optional.ofNullable(patrons.get(patronId));
    }

    @Override
    public List<Patron> findAll() {
        return patrons.values().stream().toList();
    }

    @Override
    public boolean existsByPatronId(String patronId) {
        return patrons.containsKey(patronId);
    }
}

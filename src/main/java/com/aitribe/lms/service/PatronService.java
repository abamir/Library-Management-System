package com.aitribe.lms.service;

import com.aitribe.lms.entity.Patron;

import java.util.List;

public interface PatronService {

    void addPatron(Patron patron);

    void updatePatron(String patronId, String name, String phone, String email);

    Patron getPatron(String patronId);

    List<Patron> ListAllPatrons();

}

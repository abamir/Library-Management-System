package com.aitribe.lms.service.serviceImpl;

import com.aitribe.lms.Util.ValidationUtil;
import com.aitribe.lms.entity.Patron;
import com.aitribe.lms.repository.PatronRepository;
import com.aitribe.lms.service.PatronService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class PatronServiceImpl implements PatronService {

    private static final Logger log = LoggerFactory.getLogger(PatronServiceImpl.class);

    private final PatronRepository patronRepo;

    public PatronServiceImpl(PatronRepository patronRepo) {
        this.patronRepo = patronRepo;
    }


    @Override
    public void addPatron(Patron patron) {

        //Validate
        ValidationUtil.validate(!patronRepo.existsByPatronId(patron.patronId()),
                "Patron with Id  already exist" + patron.patronId());

        patronRepo.save(patron);
        log.info("Patron added {} ", patron);


    }

    @Override
    public void updatePatron(String patronId, String name, String phone, String email) {

        Optional<Patron> existing = patronRepo.findByPatronId(patronId);

        ValidationUtil.validate(existing.isPresent(), "Patron with Id not exist" + patronId);

        existing.get().setName(name);
        existing.get().setPhone(phone);
        existing.get().setEmail(email);

        patronRepo.save(existing.get());
        log.info("Patron updated {}", existing.get());

    }

    @Override
    public Patron getPatron(String patronId) {
        return patronRepo.findByPatronId(patronId)
                .orElseThrow(() -> new RuntimeException("Patron not found with Id " + patronId));
    }

    @Override
    public List<Patron> ListAllPatrons() {
        return patronRepo.findAll();
    }
}

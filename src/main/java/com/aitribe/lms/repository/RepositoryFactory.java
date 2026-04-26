package com.aitribe.lms.repository;


import com.aitribe.lms.repository.inmemory.*;

public final class RepositoryFactory {


    private RepositoryFactory() {

    }


    public static BookRepository createBookRepository() {
        return new BookRepositoryImpl();
    }


    public static PatronRepository createPatronRepository() {
        return new PatronRepositoryImpl();
    }

    public static LoanRepository createLoanRepository() {

        return new LoanRepositoryImpl();
    }

    public static BranchRepositoryImpl createBranchRepository() {

        return new BranchRepositoryImpl();
    }

    public static ReservationRepository createReservationRepository() {
        return new ReservationRepositoryImpl();
    }

}

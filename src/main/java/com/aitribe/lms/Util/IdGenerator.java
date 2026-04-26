package com.aitribe.lms.Util;

import java.util.UUID;

public class IdGenerator {

    private IdGenerator() {

    }

    public static String generateLoanId() {


        return "LN-" + UUID.randomUUID().toString()
                .substring(0, 8)
                .toUpperCase();
    }

    public static String generateReservationId() {
        return "RSV-" + UUID.randomUUID().toString()
                .substring(0, 8)
                .toUpperCase();
    }
}

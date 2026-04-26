package com.aitribe.lms.Util;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static void validate(boolean condition, String message) {

        if (!condition) {

            throw new IllegalArgumentException(message);
        }
    }
}

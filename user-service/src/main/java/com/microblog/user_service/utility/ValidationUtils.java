package com.microblog.user_service.utility;

public class ValidationUtils {

    private ValidationUtils() {

    }

    // Checks if the input contains only letters and digits
    public static boolean isAlphanumeric(String input) {
        return input != null && input.matches("^[a-zA-Z0-9 ]+$");
    }

    // Checks if the input is a valid email format
    public static boolean isValidEmail(String input) {
        return input != null
                && input.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
}
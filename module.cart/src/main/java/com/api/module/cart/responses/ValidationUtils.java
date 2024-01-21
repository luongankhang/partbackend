package com.api.module.cart.responses;

import java.util.UUID;

public class ValidationUtils {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isValidName(String name) {
        String regex = "^[a-zA-Z]+$";
        return name.matches(regex);
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        return email.matches(regex);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^\\d{10}$";
        return phoneNumber.matches(regex);
    }

    public static boolean existsFormById(UUID id) {
        try {
            final UUID uuid = UUID.fromString(String.valueOf(id));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
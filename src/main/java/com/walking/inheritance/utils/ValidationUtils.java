package com.walking.inheritance.utils;

public class ValidationUtils {
    private ValidationUtils() {
    }

    public static <T> void requireNull(T object, String fieldName) {
        if (object != null) {
            throw new IllegalArgumentException("Поле '%s' должно быть не заполнено".formatted(fieldName));
        }
    }
}

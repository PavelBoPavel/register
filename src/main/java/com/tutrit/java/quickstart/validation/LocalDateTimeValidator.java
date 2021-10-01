package com.tutrit.java.quickstart.validation;

import com.tutrit.java.quickstart.exception.LocalDateTimeException;

import java.time.LocalDateTime;
import java.util.Objects;

public class LocalDateTimeValidator {
    public static void checkLocalDateTimeNotNull(LocalDateTime dateTime) {
        if (Objects.isNull(dateTime) ){
            throw new LocalDateTimeException("LocalDateTime shouldn't be null");
        }
    }
}

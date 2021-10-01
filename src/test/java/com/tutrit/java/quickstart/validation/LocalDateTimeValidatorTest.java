package com.tutrit.java.quickstart.validation;

import com.tutrit.java.quickstart.exception.LocalDateTimeException;
import org.junit.Test;

public class LocalDateTimeValidatorTest {

    @Test(expected = LocalDateTimeException.class)
    public void checkLocalDateTimeNotNull() {
        LocalDateTimeValidator.checkLocalDateTimeNotNull(null);
    }
}

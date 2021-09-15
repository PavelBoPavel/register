package com.tutrit.java.quickstart.validation;

import com.tutrit.java.quickstart.bean.User;
import com.tutrit.java.quickstart.exception.UserValidationException;

import java.util.List;

public class UserValidator {

    private static final List<UserCheckable> userCheck = List.of(
            new CheckUserNotNull(),
            new CheckUserNameAndSurnameNotNull(),
            //new CheckUserNameAndSurnameLength(),
            new CheckUserSlot()
    );

    private UserValidator() {
    }

    public static void validation(User user) throws UserValidationException {
        userCheck.forEach(u -> u.check(user));
    }
}

package com.tutrit.java.quickstart.validation;

import com.tutrit.java.quickstart.bean.User;
import com.tutrit.java.quickstart.exception.UserValidationException;

public class CheckUserNotNull implements Checkable {
    @Override
    public void check(User user) throws UserValidationException {
        if (user == null) {
            throw new UserValidationException("User must be not null!!");
        }
    }
}

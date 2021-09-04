package com.tutrit.java.quickstart.validation;

import com.tutrit.java.quickstart.bean.User;
import com.tutrit.java.quickstart.exception.UserValidationException;

public class CheckUserNameAndSurnameNotNull implements UserCheckable {
    @Override
    public void check(User user) throws UserValidationException {
        if (user.getName() == null || user.getSurname() == null) {
            throw new UserValidationException("Name or Surname User can't be null!!");
        }
    }
}

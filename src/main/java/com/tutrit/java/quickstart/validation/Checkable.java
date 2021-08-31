package com.tutrit.java.quickstart.validation;

import com.tutrit.java.quickstart.bean.User;
import com.tutrit.java.quickstart.exception.UserValidationException;

public interface Checkable {

    void check(User user) throws UserValidationException;
}

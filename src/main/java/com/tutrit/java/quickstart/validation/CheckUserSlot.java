package com.tutrit.java.quickstart.validation;

import com.tutrit.java.quickstart.bean.User;
import com.tutrit.java.quickstart.exception.UserValidationException;

public class CheckUserSlot implements Checkable  {
    @Override
    public void check(User user) throws UserValidationException {
        if (user.getSlots() == null) {
            throw new UserValidationException("Slot can't be null!!");
        }
    }
}

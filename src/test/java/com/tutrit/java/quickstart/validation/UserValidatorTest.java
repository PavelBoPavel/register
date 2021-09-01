package com.tutrit.java.quickstart.validation;

import com.tutrit.java.quickstart.bean.User;
import com.tutrit.java.quickstart.exception.UserValidationException;
import org.junit.Test;

import static com.tutrit.java.quickstart.validation.UserValidator.validation;

public class UserValidatorTest  {

    @Test(expected = UserValidationException.class)
    public void validationUserName() {
        var userTest = new User(null, "Bakkunio");
        validation(userTest);
    }

    @Test(expected = UserValidationException.class)
    public void validationUserSurname() {
        var userTest = new User("Bakke", null);
        validation(userTest);
    }

    @Test(expected = UserValidationException.class)
    public void validationUserSlot() {
        var userTest = new User("Bakke", "Bakkunio", null);
        validation(userTest);
    }

    @Test(expected = UserValidationException.class)
    public void validationUserNotNull() {
        validation(null);
    }

    @Test(expected = UserValidationException.class)
    public void validationUserNameLenght() {
        var userTest = new User("Ba", "Bakkunio", null);
        validation(userTest);
    }

    @Test(expected = UserValidationException.class)
    public void validationUserSurnameLenght() {
        var userTest = new User("Bakke", "Bakkuniojfhdgfhskrtteuiwnvcddmbciuevfrebdhhhfcdh", null);
        validation(userTest);
    }
}

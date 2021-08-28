package com.tutrit.java.quickstart.bean;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    private static final String NAME = "Denis";
    private static final String SURNAME = "Denisov";
    private User user;

    @Before
    public void setUp() {
        user = new User(NAME, SURNAME);
    }

    @Test
    public void getName() {
        Assert.assertEquals(NAME, user.getName());
    }

    @Test
    public void setName() {
        String newName = "Anton";
        user.setName(newName);
        Assert.assertEquals(newName, user.getName());
    }

    @Test
    public void getSurname() {
        Assert.assertEquals(SURNAME, user.getSurname());
    }

    @Test
    public void setSurname() {
        String newSurname = "Potato";
        user.setSurname(newSurname);
        Assert.assertEquals(newSurname, user.getSurname());
    }

    @Test
    public void getSlots() {

    }

    @Test
    public void setSlots() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testToString() {
    }
}
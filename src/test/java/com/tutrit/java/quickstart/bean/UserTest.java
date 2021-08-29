package com.tutrit.java.quickstart.bean;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserTest {
    private static final String NAME = "Denis";
    private static final String SURNAME = "Denisov";
    private User user;

    @Before
    public void setUp() {
        user = getDefaultUser();
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
        Assert.assertNull(user.getSlots());
    }

    @Test
    public void setSlots() {
        Slot slot1 = new Slot(LocalDateTime.now(), 60);
        Slot slot2 = new Slot(LocalDateTime.of(2021, 8, 28, 16, 34), 120);
        List<Slot> slots = new ArrayList<>();
        slots.add(slot1);
        slots.add(slot2);
        user.setSlots(slots);
        Assert.assertNotNull(user.getSlots());
        Assert.assertEquals(2, user.getSlots().size());
    }

    @Test
    public void testEqualsAndHashCodeContract() {
        User secondUser = getDefaultUser();
        Assert.assertEquals(secondUser, user);
        secondUser.setName("Anton");
        Assert.assertNotEquals(secondUser, user);

        secondUser = getDefaultUser();
        secondUser.setSurname("Vern");
        Assert.assertNotEquals(secondUser, user);

        EqualsVerifier.simple();
    }

    @Test
    public void testToString() {
        String expect = "User{name='Denis', surname='Denisov', slots=null}";
        Assert.assertEquals(expect, user.toString());
        ToStringVerifier.forClass(User.class).verify();
    }

    private User getDefaultUser() {
        return new User(NAME, SURNAME);
    }
}

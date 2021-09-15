package com.tutrit.java.quickstart.bean;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;


public class SlotTest {

    private Slot slot;

    @Before
    public void setup() {
        slot = new Slot(LocalDateTime.parse("2021-01-01T19:00"), 30);
    }

    @Test
    public void compareTo() {
        Slot slot2 = new Slot(LocalDateTime.parse("2021-01-01T20:00"), 30);
        Assert.assertEquals(-1, slot.compareTo(slot2));
        Slot slot3 = new Slot(LocalDateTime.parse("2020-01-01T19:00"), 30);
        Assert.assertEquals(1, slot.compareTo(slot3));
    }
}

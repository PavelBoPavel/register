package com.tutrit.java.quickstart.service;

import com.tutrit.java.quickstart.bean.Slot;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class AppointmentCalendarTest {

    AppointmentCalendar appointmentCalendar;

    @Before
    public void setup()
    {
        appointmentCalendar = new AppointmentCalendar();
        appointmentCalendar.removeAllSlots();
    }

    @Test
    public void addSlot() {
        var slot = makeSlot();
        appointmentCalendar.addSlot(slot);
        assertEquals(expectedSlotList(), appointmentCalendar.findAll());
    }

    @Test
    public void findAll() {
        makeSlots(5).forEach((dateTime, slot) ->  appointmentCalendar.addSlot(slot));
        assertEquals(5, appointmentCalendar.findAll().size());
        assertEquals(makeSlots(5), appointmentCalendar.findAll());
    }

    @Test
    public void isPresent() {
        var slotForExample = makeSlot();
        makeSlots(5).forEach((dateTime, slot) ->  appointmentCalendar.addSlot(slot));
        assertTrue(appointmentCalendar.isPresent(slotForExample.getDateTime()));
    }

    private Slot makeSlot() {
        return new Slot(LocalDateTime.parse("2021-01-01T12:00"), 30);
    }

    private  Map<LocalDateTime, Slot> makeSlots(int number) {
        return IntStream.range(1, number + 1).boxed()
                .collect(Collectors.toMap(i -> LocalDateTime.parse("2021-0" + i + "-01T12:00"), i ->  new Slot(LocalDateTime.parse("2021-0" + i + "-01T12:00"), 30)));

    }

    private Map<LocalDateTime, Slot> expectedSlotList() {
        return Map.of(LocalDateTime.parse("2021-01-01T12:00"),
                new Slot(LocalDateTime.parse("2021-01-01T12:00"), 30));
    }
}
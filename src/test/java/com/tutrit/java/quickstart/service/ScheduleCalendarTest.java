package com.tutrit.java.quickstart.service;

import com.tutrit.java.quickstart.bean.Slot;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class ScheduleCalendarTest {

    ScheduleCalendar scheduleCalendar;

    @Before
    public void setup() {
        scheduleCalendar = new ScheduleCalendar();
        scheduleCalendar.remuveAllSlots();
    }

    @Test
    public void addSlot() {
        var slot = makeSlot();
        scheduleCalendar.addSlot(slot);
        assertEquals(expectedSlotList(), scheduleCalendar.findAll());
    }

    @Test
    public void findAll() {
        makeSlots(5).forEach(slot -> scheduleCalendar.addSlot(slot));
        assertEquals(5, scheduleCalendar.findAll().size());
        assertEquals(makeSlots(5), scheduleCalendar.findAll());
    }

    private Slot makeSlot() {
        return new Slot(LocalDateTime.parse("2021-01-01T12:00"), 30);
    }

    private List<Slot> makeSlots(int number) {
        return IntStream.range(1, number + 1).boxed()
                .map(i -> new Slot(LocalDateTime.parse("2021-0" + i + "-01T12:00"), 30))
                .collect(Collectors.toList());

    }

    private List<Slot> expectedSlotList() {
       return List.of(new Slot(LocalDateTime.parse("2021-01-01T12:00"), 30));
    }
}
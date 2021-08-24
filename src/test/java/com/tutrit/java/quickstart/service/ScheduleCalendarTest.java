package com.tutrit.java.quickstart.service;

import com.tutrit.java.quickstart.bean.Slot;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class ScheduleCalendarTest {

    ScheduleCalendar scheduleCalendar;

    @Before
    public void setup() {
        scheduleCalendar = new ScheduleCalendar();
        scheduleCalendar.removeAllSlots();
    }

    @Test
    public void addSlot() {
        var slot = makeSlot();
        scheduleCalendar.addSlot(slot);
        assertEquals(expectedSlotList(), scheduleCalendar.findAll());
    }

    @Test
    public void findAll() {
        makeSlots(5).forEach((dateTime, slot) -> scheduleCalendar.addSlot(slot));
        assertEquals(5, scheduleCalendar.findAll().size());
        assertEquals(makeSlots(5), scheduleCalendar.findAll());
    }

    private Slot makeSlot() {
        return new Slot(LocalDateTime.parse("2021-01-01T12:00"), 30);
    }

    private Map<LocalDateTime, Slot> makeSlots(int number) {
        return IntStream.range(1, number + 1).boxed()
                .collect(Collectors.toMap(
                        i -> LocalDateTime.parse("2021-0" + i + "-01T12:00"),
                        i -> new Slot(LocalDateTime.parse("2021-0" + i + "-01T12:00"), 30))
                );
    }

    private Map<LocalDateTime, Slot> expectedSlotList() {
        return Map.of(LocalDateTime.parse("2021-01-01T12:00"), new Slot(LocalDateTime.parse("2021-01-01T12:00"), 30));
    }
}

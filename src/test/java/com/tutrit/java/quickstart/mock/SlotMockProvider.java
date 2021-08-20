package com.tutrit.java.quickstart.mock;

import com.tutrit.java.quickstart.bean.Slot;
import com.tutrit.java.quickstart.service.ScheduleCalendar;

import java.time.LocalDateTime;
import java.util.List;

public class SlotMockProvider {

    public static List<Slot> makeSlotsMock() {
        return List.of(
                new Slot(LocalDateTime.parse("2021-01-01T19:00"), 30),
                new Slot(LocalDateTime.parse("2021-01-01T19:30"), 30),
                new Slot(LocalDateTime.parse("2021-01-01T20:00"), 30),
                new Slot(LocalDateTime.parse("2021-01-01T20:30"), 30),
                new Slot(LocalDateTime.parse("2021-01-01T21:00"), 30)
        );
    }
}

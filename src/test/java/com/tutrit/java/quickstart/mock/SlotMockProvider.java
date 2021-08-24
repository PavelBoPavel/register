package com.tutrit.java.quickstart.mock;

import com.tutrit.java.quickstart.bean.Slot;

import java.time.LocalDateTime;
import java.util.Map;

public class SlotMockProvider {

    public static Map<LocalDateTime, Slot> makeSlotsMock() {
        return Map.of(
                LocalDateTime.parse("2021-01-01T19:00"), (new Slot(LocalDateTime.parse("2021-01-01T19:00"), 30)),
                LocalDateTime.parse("2021-01-01T19:30"), (new Slot(LocalDateTime.parse("2021-01-01T19:30"), 30)),
                LocalDateTime.parse("2021-01-01T20:00"), (new Slot(LocalDateTime.parse("2021-01-01T20:00"), 30)),
                LocalDateTime.parse("2021-01-01T20:30"), (new Slot(LocalDateTime.parse("2021-01-01T20:30"), 30)),
                LocalDateTime.parse("2021-01-01T21:00"), (new Slot(LocalDateTime.parse("2021-01-01T21:00"), 30))
        );
    }
}

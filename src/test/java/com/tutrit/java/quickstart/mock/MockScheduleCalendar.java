package com.tutrit.java.quickstart.mock;

import com.tutrit.java.quickstart.bean.Slot;
import com.tutrit.java.quickstart.repository.ScheduleCalendar;
import com.tutrit.java.quickstart.repository.ScheduleCalendarRepository;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Temp DataBase
 *
 * @see com.tutrit.java.quickstart.repository.ScheduleCalendar
 */
public class MockScheduleCalendar implements ScheduleCalendarRepository {
    public static Map<LocalDateTime, Slot> mockSlot;

    public MockScheduleCalendar() {
    }

    public static Map<LocalDateTime, Slot> makeSlotsMock() {
        return Map.of(
                LocalDateTime.parse("2021-01-01T19:00"), (new Slot(LocalDateTime.parse("2021-01-01T19:00"), 30)),
                LocalDateTime.parse("2021-01-01T19:30"), (new Slot(LocalDateTime.parse("2021-01-01T19:30"), 30)),
                LocalDateTime.parse("2021-01-01T20:00"), (new Slot(LocalDateTime.parse("2021-01-01T20:00"), 30)),
                LocalDateTime.parse("2021-01-01T20:30"), (new Slot(LocalDateTime.parse("2021-01-01T20:30"), 30)),
                LocalDateTime.parse("2021-01-01T21:00"), (new Slot(LocalDateTime.parse("2021-01-01T21:00"), 30))
        );
    }

    @Override
    public void addSlot(Slot slot) {
        mockSlot.put(slot.getDateTime(), slot);
    }

    @Override
    public Map<LocalDateTime, Slot> findAll() {
        return mockSlot;
    }

    @Override
    public void removeAllSlots() {
        mockSlot.clear();
    }

    public static Map<LocalDateTime, Slot> getMockSlot() {
        return mockSlot;
    }

    public static void setMockSlot(Map<LocalDateTime, Slot> mockSlot) {
        MockScheduleCalendar.mockSlot = mockSlot;
    }
}

package com.tutrit.java.quickstart.repository;

import com.tutrit.java.quickstart.bean.Slot;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ScheduleCalendar {
    private static final Map<LocalDateTime, Slot> scheduleCalendarMap = new HashMap<>();

    public void addSlot(Slot slot) {
        scheduleCalendarMap.put(slot.getDateTime(), slot);
    }

    public Map<LocalDateTime, Slot> findAll() {
        return scheduleCalendarMap;
    }

    public void removeAllSlots() {
        scheduleCalendarMap.clear();
    }
}

package com.tutrit.java.quickstart.service;

import com.tutrit.java.quickstart.bean.Slot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleCalendar {
    private static final Map<LocalDateTime, Slot> scheduleCalendarMap = new HashMap<>();

    public void addSlot(Slot slot) {
        scheduleCalendarMap.put(slot.getDateTime(), slot);
    }

    public Map<LocalDateTime, Slot> findAll(){
        return scheduleCalendarMap;
    }

    public void removeAllSlots() {
        scheduleCalendarMap.clear();
    }
}

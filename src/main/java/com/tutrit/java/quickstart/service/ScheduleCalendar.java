package com.tutrit.java.quickstart.service;

import com.tutrit.java.quickstart.bean.Slot;

import java.util.ArrayList;
import java.util.List;

public class ScheduleCalendar {
    private static final List<Slot> scheduleCalendarList = new ArrayList<>();

    public void addSlot(Slot slot) {
        scheduleCalendarList.add(slot);
    }

    public List<Slot> findAll(){
        return scheduleCalendarList;
    }

    public void removeAllSlots() {
        scheduleCalendarList.removeAll(findAll());
    }
}

package com.tutrit.java.quickstart.controller;

import com.tutrit.java.quickstart.bean.Slot;
import com.tutrit.java.quickstart.service.ScheduleCalendar;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleCalendarController {
    private final ScheduleCalendar scheduleCalendar;

    public ScheduleCalendarController(final ScheduleCalendar scheduleCalendar) {
        this.scheduleCalendar = scheduleCalendar;
    }

    public List<Slot> showAllSlots(){
        return scheduleCalendar.findAll();
    }

    public List<Slot> addSlot(String date, String duratoin){
        Slot slot = new Slot(LocalDateTime.parse(date), Integer.parseInt(duratoin));
        scheduleCalendar.addSlot(slot);
        return scheduleCalendar.findAll();
    }
}

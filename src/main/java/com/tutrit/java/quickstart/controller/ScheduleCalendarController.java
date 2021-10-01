package com.tutrit.java.quickstart.controller;

import com.tutrit.java.ioc.annotation.MyCommand;
import com.tutrit.java.ioc.annotation.MyComponent;
import com.tutrit.java.ioc.annotation.MyInjection;
import com.tutrit.java.quickstart.bean.Slot;
import com.tutrit.java.quickstart.repository.ScheduleCalendar;

import java.time.LocalDateTime;
import java.util.Map;

@MyComponent
public class ScheduleCalendarController {

  @MyInjection
  private ScheduleCalendar scheduleCalendar;

  public ScheduleCalendarController() {
  }

  public ScheduleCalendarController(final ScheduleCalendar scheduleCalendar) {
    this.scheduleCalendar = scheduleCalendar;
  }

  @MyCommand("/showSlots")
  public Map<LocalDateTime, Slot> showAllSlots() {
    return scheduleCalendar.findAll();
  }

  @MyCommand("/addSlot")
  public Map<LocalDateTime, Slot> addSlot(String date, String duration) {
    Slot slot = new Slot(LocalDateTime.parse(date), Integer.parseInt(duration));
    scheduleCalendar.addSlot(slot);
    return scheduleCalendar.findAll();
  }

  public Map<LocalDateTime, Slot> addBatchSlot(String[] args) {
    for (int i = 0; i < args.length; i += 2) {
      Slot slot = new Slot(LocalDateTime.parse(args[i]), Integer.parseInt(args[i + 1]));
      scheduleCalendar.addSlot(slot);
    }
    return scheduleCalendar.findAll();
  }
}

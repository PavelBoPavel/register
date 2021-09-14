package com.tutrit.java.quickstart.dispatcher;

import static com.tutrit.java.quickstart.Application.log;

import com.tutrit.java.quickstart.Application;
import com.tutrit.java.quickstart.bean.Slot;
import com.tutrit.java.quickstart.controller.ScheduleCalendarController;

import com.tutrit.java.quickstart.service.ScheduleCalendar;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class BaseDispatcher {
    private final ScheduleCalendarController scheduleCalendarController;

    public BaseDispatcher(final ScheduleCalendarController scheduleCalendarController) {
        this.scheduleCalendarController = scheduleCalendarController;
    }

    public Map<LocalDateTime, Slot> dispatch(String[] args) {
        Map<LocalDateTime, Slot> result = null;
        switch (args[0]) {
            case "/showSlots":
                result = scheduleCalendarController.showAllSlots();
                break;
            case "/addSlot":
                result = scheduleCalendarController.addSlot(args[1], args[2]);
                break;
            case "/addBatchSlots":
                result = scheduleCalendarController.addSlot(args[1], args[2]);
                break;
            default:
                log.info("Unknown command", args[0]);
                result = new HashMap<>();
        }
        return result;
    }

    public static void run(String[] args) {
        var calendar = new ScheduleCalendar();
        calendar.loadFromFile();
        new BaseDispatcher(new ScheduleCalendarController(calendar))
            .dispatch(args)
            .values()
            .forEach(slot -> log.info("{}", slot));
        calendar.saveToFile();
    }
}

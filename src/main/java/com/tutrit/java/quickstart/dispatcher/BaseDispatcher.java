package com.tutrit.java.quickstart.dispatcher;

import com.tutrit.java.quickstart.bean.Slot;
import com.tutrit.java.quickstart.controller.ScheduleCalendarController;

import java.util.List;

public class BaseDispatcher {
    private final ScheduleCalendarController scheduleCalendarController;

    public BaseDispatcher(final ScheduleCalendarController scheduleCalendarController) {
        this.scheduleCalendarController = scheduleCalendarController;
    }

    public List<Slot> dispatch(String[] args) {
        List<Slot> result = null;
        switch (args[0]) {
            case "/showSlots":
                return scheduleCalendarController.showAllSlots();
            case "/addSlot":
                result = scheduleCalendarController.addSlot(args[1], args[2]);
                result.forEach(System.out::println);
                break;
            case "/addBatchSlots":
                result = scheduleCalendarController.addSlot(args[1], args[2]);
                result.forEach(System.out::println);
                break;
        }
        return result;
    }
}

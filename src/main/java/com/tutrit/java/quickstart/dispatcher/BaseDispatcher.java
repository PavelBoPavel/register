package com.tutrit.java.quickstart.dispatcher;

import com.tutrit.java.quickstart.bean.Slot;
import com.tutrit.java.quickstart.controller.ScheduleCalendarController;

import java.time.LocalDateTime;
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
                return scheduleCalendarController.showAllSlots();
            case "/addSlot":
                result = scheduleCalendarController.addSlot(args[1], args[2]);
                break;
            case "/addBatchSlots":
                result = scheduleCalendarController.addSlot(args[1], args[2]);
                break;
        }
        return result;
    }
}

package com.tutrit.java.quickstart.spy;

import com.tutrit.java.quickstart.bean.Slot;
import com.tutrit.java.quickstart.controller.ScheduleCalendarController;
import com.tutrit.java.quickstart.service.ScheduleCalendar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleCalendarControllerSpy extends ScheduleCalendarController {
    private final Map<String, Integer> verify = new HashMap<>();

    public ScheduleCalendarControllerSpy(ScheduleCalendar scheduleCalendar) {
        super(scheduleCalendar);
    }

    @Override
    public List<Slot> showAllSlots(){
        if (verify.containsKey("showAllSlots")){
            verify.put("showAllSlots", verify.get("showAllSlots") + 1);
        }
        verify.put("showAllSlots", 1);
        return super.showAllSlots();
    }

    public boolean verify(String method, int times) {
        return verify.get(method).equals(times);
    }
}

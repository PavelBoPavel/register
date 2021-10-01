package com.tutrit.java.quickstart.dispatcher;

import com.tutrit.java.quickstart.bean.Slot;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    String key;//example "/showSlots";
    Object obj;//  example: ScheduleCalendarController
    Method method;// example: showAllSlots(String[] args)

    public Map<LocalDateTime, Slot> execute(String[] args) {
        return new HashMap<>();//TODO
    }
}

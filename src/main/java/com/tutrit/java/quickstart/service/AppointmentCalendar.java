package com.tutrit.java.quickstart.service;

import com.tutrit.java.quickstart.bean.Slot;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentCalendar {
    private static final Map<LocalDateTime, Slot> appointmentCalendarMap = new HashMap<>();

    public void addSlot(Slot slot) {
        appointmentCalendarMap.put(slot.getDateTime(), slot);
    }

    public Map<LocalDateTime, Slot> findAll(){
        return appointmentCalendarMap;
    }

    public Slot findByDateTime(LocalDateTime dateTime) {
        return appointmentCalendarMap.get(dateTime);
    }

    public boolean isPresent(LocalDateTime dateTime) {
        return appointmentCalendarMap.containsKey(dateTime);
    }

    public void removeAllSlots() {
        appointmentCalendarMap.clear();
    }
}

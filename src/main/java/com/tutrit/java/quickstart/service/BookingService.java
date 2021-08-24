package com.tutrit.java.quickstart.service;

import com.tutrit.java.quickstart.bean.Slot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingService {

    private final AppointmentCalendar appointmentCalendar;
    private final ScheduleCalendar scheduleCalendar;

    public BookingService(AppointmentCalendar appointmentCalendar, ScheduleCalendar scheduleCalendar) {
        this.appointmentCalendar = appointmentCalendar;
        this.scheduleCalendar = scheduleCalendar;
    }

    public boolean isAvailableForBooking(Slot slot){
        return appointmentCalendar.isPresent(slot.getDateTime());
    }

    public Map<LocalDateTime, Slot> findAllAvailableSlots(){
        Map<LocalDateTime, Slot> allSlots = new HashMap<>(scheduleCalendar.findAll());
        Map<LocalDateTime, Slot> freeSlots = new HashMap<>();
        for(Map.Entry<LocalDateTime,Slot> slot : allSlots.entrySet()) {
            if(!appointmentCalendar.isPresent(slot.getKey())) {
                freeSlots.put(slot.getKey(), slot.getValue());
            }
        }
        return freeSlots;
    }
}

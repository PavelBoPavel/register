package com.tutrit.java.quickstart.service;

import com.tutrit.java.quickstart.bean.Slot;

import java.util.ArrayList;
import java.util.List;

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

    public List<Slot> findAllAvailableSlots(){
        List<Slot> allSlots = new ArrayList<>(scheduleCalendar.findAll());
        List<Slot> freeSlots = new ArrayList<>();
        for(Slot slot : allSlots) {
            if(!appointmentCalendar.isPresent(slot.getDateTime())) {
                freeSlots.add(slot);
            }
        }
        return freeSlots;
    }
}

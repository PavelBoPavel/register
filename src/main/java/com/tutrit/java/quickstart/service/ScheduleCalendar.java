package com.tutrit.java.quickstart.service;

import com.tutrit.java.ioc.annotation.MyComponent;
import com.tutrit.java.ioc.annotation.MyInjection;
import com.tutrit.java.quickstart.bean.Slot;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@MyComponent
public class ScheduleCalendar implements Serializable {

    @MyInjection
    SlotService slotService;

    public void addSlot(Slot slot) {
        slotService.save(slot);
    }

    public Map<LocalDateTime, Slot> findAll() {
        return slotService.findAll();
    }

    public void removeAllSlots() {
        slotService.deleteAll();
    }
}

package com.tutrit.java.quickstart.repository;

import com.tutrit.java.quickstart.bean.Slot;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ScheduleCalendar implements ScheduleCalendarRepository {
    //TODO @KaydunovDenis Считаю необходима смена hashMap на List
    // Тут происходит не нужное дублирование данных, а именно - LocalDateTime
    // LocalDateTime является полем слота - SLot
    // Кроме того HashMap не позволяет хранить два слота на одну дату,
    // что ограничивает функциональность приложения
    private static final Map<LocalDateTime, Slot> scheduleCalendarMap = new HashMap<>();

    @Override
    public void addSlot(Slot slot) {
        scheduleCalendarMap.put(slot.getDateTime(), slot);
    }

    @Override
    public Map<LocalDateTime, Slot> findAll() {
        return scheduleCalendarMap;
    }

    @Override
    public void removeAllSlots() {
        scheduleCalendarMap.clear();
    }
}

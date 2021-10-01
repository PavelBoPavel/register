package com.tutrit.java.quickstart.repository;

import com.tutrit.java.quickstart.bean.Slot;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * This is interface marker
 * It needs for the testing ScheduleCalendarService
 * @see ScheduleCalendar
 */
public interface ScheduleCalendarRepository {
    //TODO Если разобраться как правильно использовать Mockito то этот маркер можно удалить

    void addSlot(Slot slot);

    Map<LocalDateTime, Slot> findAll();

    void removeAllSlots();
}

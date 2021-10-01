package com.tutrit.java.quickstart.service;

import com.tutrit.java.quickstart.bean.Slot;
import com.tutrit.java.quickstart.repository.ScheduleCalendar;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleCalendarService {
    /**
     * Когда пользователь вводит дату, он видит список всех слотов записей на этот день.
     */
    public List<Slot> findAllByDate(ScheduleCalendar calendar, LocalDateTime dateTime) {
        //TODO тут проблема читать подробнее в JIRA
        return List.of(calendar.findAll().get(dateTime));
    }

    /**
     * Когда пользователь вводит дату, он видит список свободных слотов записей на этот день.
     */
    public List<Slot> findAllAvailableByDate(ScheduleCalendar calendar, LocalDateTime dateTime) {
        //TODO тут проблема читать подробнее в JIRA
        return List.of(calendar.findAll().get(dateTime));
    }

    /**
     * Когда пользователь вводит дату, он видит список занятых слотов записей на этот день.
     */
    public List<Slot> findAllNotAvailableByDate(ScheduleCalendar calendar, LocalDateTime dateTime) {
        //TODO тут проблема читать подробнее в JIRA
        return List.of(calendar.findAll().get(dateTime));
    }
}

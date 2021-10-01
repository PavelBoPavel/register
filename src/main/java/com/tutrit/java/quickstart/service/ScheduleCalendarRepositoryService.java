package com.tutrit.java.quickstart.service;

import com.tutrit.java.quickstart.bean.Slot;
import com.tutrit.java.quickstart.exception.ScheduleCalendarException;
import com.tutrit.java.quickstart.repository.ScheduleCalendar;
import com.tutrit.java.quickstart.repository.ScheduleCalendarRepository;
import com.tutrit.java.quickstart.validation.LocalDateTimeValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ScheduleCalendarRepositoryService {
    /**
     * Когда пользователь вводит дату, он видит список всех слотов записей на этот день.
     */
    public List<Slot> findAllByDate(ScheduleCalendarRepository calendar, LocalDateTime dateTime) {
        //TODO тут возможно проблема читать подробнее в JIRA https://jis8.atlassian.net/browse/FBB-40
        validate(calendar, dateTime);
        return List.of(calendar.findAll().get(dateTime));
    }

    private void validate(ScheduleCalendarRepository calendar, LocalDateTime dateTime) {
        checkScheduleCalendarNotNull(calendar);
        LocalDateTimeValidator.checkLocalDateTimeNotNull(dateTime);
    }

    public void checkScheduleCalendarNotNull(ScheduleCalendarRepository calendar) {
        if (Objects.isNull(calendar)) {
            throw new ScheduleCalendarException("ScheduleCalendar shouldn't be null");
        }
    }

    /**
     * Когда пользователь вводит дату, он видит список свободных слотов записей на этот день.
     */
    public List<Slot> findAllAvailableByDate(ScheduleCalendarRepository calendar, LocalDateTime dateTime) {
        //TODO тут возможно проблема читать подробнее в JIRA https://jis8.atlassian.net/browse/FBB-40
        //TODO BLOCKED для логической реализации этой функции у поля Slot должно быть поле
        // "Количество свободных мест" https://jis8.atlassian.net/browse/FBB-48
        validate(calendar, dateTime);
        return List.of(calendar.findAll().get(dateTime));
    }

    /**
     * Когда пользователь вводит дату, он видит список занятых слотов записей на этот день.
     */
    public List<Slot> findAllNotAvailableByDate(ScheduleCalendarRepository calendar, LocalDateTime dateTime) {
        //TODO тут возможно проблема читать подробнее в JIRA https://jis8.atlassian.net/browse/FBB-40
        validate(calendar, dateTime);
        return List.of(calendar.findAll().get(dateTime));
    }
}

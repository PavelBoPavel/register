package com.tutrit.java.quickstart.service;

public class ScheduleCalendarService {
    /**
     * Когда пользователь вводит дату, он видит список все слоты записей на этот день.
     * @param dateTime
     * @return
     */
    public List<Slot> findAllByDate(LocalDateTime dateTime) {

    }

    /**
     * Когда пользователь вводит дату, он видит список свободных слотов записей на этот день.
     * @param dateTime
     * @return
     */
    public List<Slot> findAllAvailableByDate(LocalDateTime dateTime) {
    }

    /**
     * Когда пользователь вводит дату, он видит список занятых слотов записей на этот день.
     * @param dateTime
     * @return
     */
    public List<Slot> findAllAvailableByDate(LocalDateTime dateTime) {
    }
}

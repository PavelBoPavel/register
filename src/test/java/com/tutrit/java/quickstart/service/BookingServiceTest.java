package com.tutrit.java.quickstart.service;

import com.tutrit.java.quickstart.bean.Slot;
import com.tutrit.java.quickstart.mock.SlotMockProvider;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class BookingServiceTest {

    private ScheduleCalendar scheduleCalendarMock;
    private AppointmentCalendar appointmentCalendarSpy;
    private BookingService bookingService;

    @Before
    public void setup() {
        scheduleCalendarMock = Mockito.mock(ScheduleCalendar.class);
        appointmentCalendarSpy = Mockito.spy(AppointmentCalendar.class);
        bookingService = new BookingService(appointmentCalendarSpy, scheduleCalendarMock);
    }

    @Test
    public void isAvailableForBooking() {
    }

    @Test
    public void findAllAvailableSlots() {
        appointmentCalendarSpy.addSlot(new Slot(LocalDateTime.parse("2021-01-01T19:30"), 30));
        Mockito.when(scheduleCalendarMock.findAll()).thenReturn(SlotMockProvider.makeSlotsMock());
        List<Slot> actual = bookingService.findAllAvailableSlots();
        assertEquals(getExpectedWhenFindAllAvailableSlots(), actual);
    }

    private List<Slot> getExpectedWhenFindAllAvailableSlots() {
        return List.of(
                new Slot(LocalDateTime.parse("2021-01-01T19:00"), 30),
                new Slot(LocalDateTime.parse("2021-01-01T20:00"), 30),
                new Slot(LocalDateTime.parse("2021-01-01T20:30"), 30),
                new Slot(LocalDateTime.parse("2021-01-01T21:00"), 30)
        );
    }
}
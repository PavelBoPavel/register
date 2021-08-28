package com.tutrit.java.quickstart.controller;

import com.tutrit.java.quickstart.bean.Slot;
import com.tutrit.java.quickstart.mock.SlotMockProvider;
import com.tutrit.java.quickstart.service.ScheduleCalendar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Map;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleCalendarControllerTest {

    @Mock
    private ScheduleCalendar scheduleCalendarMock;
    @InjectMocks
    private ScheduleCalendarController scheduleCalendarController;

    @Test
    public void showAllSlots() {
        Mockito.when(scheduleCalendarMock.findAll()).thenReturn(SlotMockProvider.makeSlotsMock());
        Map<LocalDateTime, Slot> actual = scheduleCalendarController.showAllSlots();
        assertEquals(expectedSlots(), actual);
    }

    private Map<LocalDateTime, Slot> expectedSlots() {
        return Map.of(
                LocalDateTime.parse("2021-01-01T19:00"), (new Slot(LocalDateTime.parse("2021-01-01T19:00"), 30)),
                LocalDateTime.parse("2021-01-01T19:30"), (new Slot(LocalDateTime.parse("2021-01-01T19:30"), 30)),
                LocalDateTime.parse("2021-01-01T20:00"), (new Slot(LocalDateTime.parse("2021-01-01T20:00"), 30)),
                LocalDateTime.parse("2021-01-01T20:30"), (new Slot(LocalDateTime.parse("2021-01-01T20:30"), 30)),
                LocalDateTime.parse("2021-01-01T21:00"), (new Slot(LocalDateTime.parse("2021-01-01T21:00"), 30))
        );
    }
}

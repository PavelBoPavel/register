package com.tutrit.java.quickstart.integration;

import com.tutrit.java.quickstart.bean.Slot;
import com.tutrit.java.quickstart.controller.ScheduleCalendarController;
import com.tutrit.java.quickstart.dispatcher.BaseDispatcher;
import com.tutrit.java.quickstart.mock.SlotMockProvider;
import com.tutrit.java.quickstart.service.ScheduleCalendar;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class IntBaseDispatchTest {

    private BaseDispatcher baseDispatcher;
    private ScheduleCalendarController controllerSpy;
    private ScheduleCalendar scheduleCalendarMock;

    @Before
    public void setup() {
        this.scheduleCalendarMock = Mockito.mock(ScheduleCalendar.class);
        this.controllerSpy = Mockito.spy(new ScheduleCalendarController(scheduleCalendarMock));
        this.baseDispatcher = new BaseDispatcher(this.controllerSpy);
    }

    @Test
    public void dispatch() {
        Mockito.when(scheduleCalendarMock.findAll()).thenReturn(SlotMockProvider.makeSlotsMock());
        String[] args = {"/showSlots"};
        Map<LocalDateTime, Slot> actual = baseDispatcher.dispatch(args);
        assertEquals(expectedSlots(), actual);
        Mockito.verify(controllerSpy).showAllSlots();
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

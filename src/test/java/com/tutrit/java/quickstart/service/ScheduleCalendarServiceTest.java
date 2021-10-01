package com.tutrit.java.quickstart.service;

import com.tutrit.java.quickstart.bean.Slot;
import com.tutrit.java.quickstart.exception.ScheduleCalendarException;
import com.tutrit.java.quickstart.mock.MockScheduleCalendar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleCalendarServiceTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ScheduleCalendarRepositoryService service = new ScheduleCalendarRepositoryService();
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.parse("2021-01-01T19:00");
    private MockScheduleCalendar mockScheduleCalendar;

    @Before
    public void setUp()  {
        mockScheduleCalendar = new MockScheduleCalendar();
        MockScheduleCalendar.mockSlot = MockScheduleCalendar.makeSlotsMock();
    }

    @Test
    public void findAllByDate() {
        Slot slotExpect = MockScheduleCalendar.makeSlotsMock().get(LOCAL_DATE_TIME);
        List<Slot> expect = List.of(slotExpect);
        logger.info("\n\texpect: {}", expect);
        List<Slot> result = service.findAllByDate(mockScheduleCalendar, LOCAL_DATE_TIME);
        logger.info("\n\tresult: {}", result.toString());

        Assert.assertEquals(expect, result);
    }

    @Ignore("https://jis8.atlassian.net/browse/FBB-48")
    @Test
    public void findAllAvailableByDate() {
        Slot slotExpect = MockScheduleCalendar.makeSlotsMock().get(LOCAL_DATE_TIME);
        List<Slot> expect = List.of(slotExpect);
        logger.info("\n\texpect: {}", expect);
        List<Slot> result = service.findAllByDate(mockScheduleCalendar, LOCAL_DATE_TIME);
        logger.info("\n\tresult: {}", result.toString());

        Assert.assertEquals(expect, result);
    }

    @Ignore("https://jis8.atlassian.net/browse/FBB-48")
    @Test
    public void findAllNotAvailableByDate() {
        Slot slotExpect = MockScheduleCalendar.makeSlotsMock().get(LOCAL_DATE_TIME);
        List<Slot> expect = List.of(slotExpect);
        logger.info("\n\texpect: {}", expect);
        List<Slot> result = service.findAllByDate(mockScheduleCalendar, LOCAL_DATE_TIME);
        logger.info("\n\tresult: {}", result.toString());

        Assert.assertEquals(expect, result);
    }

    @Test(expected = ScheduleCalendarException.class)
    public void checkScheduleCalendarNotNull() {
        service.checkScheduleCalendarNotNull(null);
    }
}

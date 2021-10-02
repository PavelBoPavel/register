package com.tutrit.java.ioc.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.tutrit.java.quickstart.bean.Slot;
import com.tutrit.java.quickstart.controller.ScheduleCalendarController;
import com.tutrit.java.quickstart.service.SlotService;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class CommandContextTest extends CommandContext {

  @Test
  public void loadContextTest() {
    Map<String, Object> ctx = Context.loadContext();
    assertNotNull(ctx);
    assertTrue(ctx.size() > 0);
  }

  @Test
  public void filterMyCommandMethodsTest() {
    Map<String, Class<?>> classMap = new HashMap<>();
    classMap.put("com.tutrit.java.quickstart.controller.ScheduleCalendarController",
        ScheduleCalendarController.class);
    classMap.put("com.tutrit.java.quickstart.service.SlotService",
        SlotService.class);
    Map<String, Method> fieldMap = CommandContext.createMethodsMap(classMap);
    Map<String, Method> commandsMap = CommandContext.filterMyCommand(fieldMap);
    assertTrue(commandsMap.size() == 2);
  }

  @Test
  public void createCommandNamesMap() {
    Map<String, Class<?>> classMap = new HashMap<>();
    classMap.put("com.tutrit.java.quickstart.controller.ScheduleCalendarController",
        ScheduleCalendarController.class);
    classMap.put("com.tutrit.java.quickstart.service.SlotService",
        SlotService.class);
    Map<String, Method> fieldMap = CommandContext.createMethodsMap(classMap);
    Map<String, Method> commandsMap = CommandContext.filterMyCommand(fieldMap);

    Map<String, Method> commandNamesMap = CommandContext.createCommandNamesMap(commandsMap);
    assertTrue(commandNamesMap.size() == 2);
  }


  @Test
  public void executeMethodByKey() throws InvocationTargetException, IllegalAccessException {
    Context.loadContext();
    LocalDateTime expectedKey = LocalDateTime.parse("2021-01-10T12:00");
    Slot expectedSlot = new Slot(LocalDateTime.parse("2021-01-10T12:00"), 30L);

    String[] args = {"/addSlot", "2021-01-10T12:00", "30"};
    Map<LocalDateTime, Slot> result = CommandContext.execute(args);
    assertNotNull(result);
  }

  @Test
  public void isMyCommandTest() throws NoSuchMethodException {
    Class clazz = ScheduleCalendarController.class;
    Method method = clazz.getMethod("showAllSlots");
    assertTrue(CommandContext.isMyCommand(method));
  }

  @Test
  public void createMethodsMapTest() {
    Map<String, Class<?>> classMap = new HashMap<>();
    classMap.put("com.tutrit.java.quickstart.controller.ScheduleCalendarController",
        ScheduleCalendarController.class);
    Map<String, Method> methodsMap = CommandContext.createMethodsMap(classMap);
    assertTrue(methodsMap.size() > 0);
  }

  @Test
  public void readMyCommandTest() throws NoSuchMethodException {
    Class clazz = ScheduleCalendarController.class;
    Method methods = clazz.getMethod("showAllSlots");
    CommandContext.readMyCommand(methods);
  }
}
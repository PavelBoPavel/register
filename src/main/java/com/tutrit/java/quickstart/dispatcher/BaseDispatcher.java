package com.tutrit.java.quickstart.dispatcher;

import com.tutrit.java.ioc.annotation.MyComponent;
import com.tutrit.java.ioc.annotation.MyInjection;
import com.tutrit.java.ioc.config.ApplicationConfiguration;
import com.tutrit.java.quickstart.bean.Slot;
import com.tutrit.java.quickstart.controller.ScheduleCalendarController;
import com.tutrit.java.quickstart.service.UserService;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MyComponent
public class BaseDispatcher {

  @MyInjection
  private ScheduleCalendarController scheduleCalendarController;
  @MyInjection
  private UserService userService;
  @MyInjection
  private ApplicationConfiguration applicationConfiguration;
  private final Logger log = LoggerFactory.getLogger("BaseDispatcher");

  public BaseDispatcher() {
  }

  public BaseDispatcher(final ScheduleCalendarController scheduleCalendarController) {
    this.scheduleCalendarController = scheduleCalendarController;
  }

  public Map<LocalDateTime, Slot> dispatch(String[] args) {
    Map<LocalDateTime, Slot> result = null;
    switch (args[0]) {
      case "/showSlots":
        result = scheduleCalendarController.showAllSlots();
        break;
      case "/addSlot":
        result = scheduleCalendarController.addSlot(args[1], args[2]);
        break;
      case "/addBatchSlots":
        result = scheduleCalendarController.addSlot(args[1], args[2]);
        break;
      default:
        log.info("Unknown command", args[0]);
        result = new HashMap<>();
    }
    return result;
  }

  // 1. Load context
  // 2. Filter all @MyCommand Methods
  // 3-4. Map annotation value as key, Method as value
  // 3-4. Create class key, method, object
  // 5. Get Method from context by key, execute method.

  /** class Dispatcher {
        String key = "/showSlots";
        Object obj = ScheduleCalendarController
        Method mth = showAllSlots(String[] args)
  }
   **/

  /**
   public Map<LocalDateTime, Slot> dispatch(String[] args) {
      Dispatcher dispatcher = (Dispatcher) dispatcherCtx.get(args[0])
      return dispatcher.execute(args)
   }
   **/

  // Read application configuration properties and inject into Base Dispatcher
}

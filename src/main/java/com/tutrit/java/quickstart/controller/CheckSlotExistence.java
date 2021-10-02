package com.tutrit.java.quickstart.controller;

import com.tutrit.java.ioc.annotation.MyCommand;
import com.tutrit.java.ioc.annotation.MyComponent;
import com.tutrit.java.quickstart.bean.Slot;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@MyComponent
public class CheckSlotExistence {

  @MyCommand("/checkIfExist")
  public Map<LocalDateTime, Slot> checkIfExist(String date, String duration) {
    System.out.println("I'm working!");
    return new HashMap<>();
  }
}

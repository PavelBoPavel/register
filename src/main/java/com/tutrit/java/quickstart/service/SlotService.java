package com.tutrit.java.quickstart.service;

import com.tutrit.java.ioc.annotation.MyComponent;
import com.tutrit.java.ioc.annotation.MyInjection;
import com.tutrit.java.quickstart.bean.Slot;
import com.tutrit.java.quickstart.repository.SlotRepository;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@MyComponent
public class SlotService {

  @MyInjection
  SlotRepository slotRepository;


  public void save(Slot slot) {
    slotRepository.create(slot);
  }

  public Map<LocalDateTime, Slot> findAll() {
    return StreamSupport.stream(slotRepository.findAll().spliterator(), false)
        .collect(Collectors.toMap(slot -> slot.getDateTime(), slot -> slot));
  }

  public void deleteAll() {
    findAll().values().stream()
        .forEach(slot -> slotRepository.delete(slot));
  }
}

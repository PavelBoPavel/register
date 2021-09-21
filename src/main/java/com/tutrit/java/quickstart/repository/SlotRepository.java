package com.tutrit.java.quickstart.repository;

import com.tutrit.java.ioc.annotation.MyComponent;
import com.tutrit.java.quickstart.bean.Slot;
import com.tutrit.java.quickstart.config.Storage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@MyComponent
public class SlotRepository implements CrudRepository<Slot, LocalDateTime> {

  @Override
  public Slot create(Slot slot) {
    if (!Files.exists(Storage.slotStorage)) {
      try {
        Files.createDirectories(Storage.slotStorage);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    Path slotFile = Path.of(Storage.slotStorage.toString(),
        slot.getDateTime().toString() + Storage.extension);
    try {
      Files.createFile(slotFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
    try (ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream(slotFile.toString()))) {
      oos.writeObject(slot);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return findById(slot.getDateTime()).orElseThrow();
  }

  @Override
  public Optional<Slot> findById(LocalDateTime id) {
    Optional<Path> slotPath = Optional.empty();
    String idAsString = id.toString();
    try {
      slotPath = Files.walk(Storage.slotStorage)
          .filter(path -> path.toString().endsWith(".slot"))
          .filter(path -> path.toString().startsWith(idAsString))
          .findFirst();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return slotPath.
        map(path -> {
          Slot slot = null;
          try {
            slot = (Slot) new ObjectInputStream(
                new FileInputStream(path.toString()))
                .readObject();
          } catch (IOException e) {
            e.printStackTrace();
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
          return slot;
        });
  }

  @Override
  public Iterable<Slot> findAll() {
    List<Path> slotsPaths = null;
    List<Slot> slots = new ArrayList<>();
    try {
      slotsPaths = Files.walk(Storage.slotStorage)
          .filter(path -> path.toString().endsWith(".slot"))
          .collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (Path path : slotsPaths) {
      Slot slot = null;
      try {
        slot = (Slot) new ObjectInputStream(
            new FileInputStream(path.toString()))
            .readObject();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      slots.add(slot);
    }
    return slots;
  }

  @Override
  public Slot update(Slot slot) {
    delete(slot);
    return create(slot);
  }

  @Override
  public void deleteById(LocalDateTime id) {
    Optional<Path> slotPath = Optional.empty();
    String idAsString = id.toString();
    try {
      slotPath = Files.walk(Storage.slotStorage)
          .filter(path -> path.toString().endsWith(".slot"))
          .filter(path -> path.toString().startsWith(idAsString))
          .findFirst();
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (slotPath.isPresent()) {
      try {
        Files.delete(slotPath.get());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }


  @Override
  public void delete(Slot slot) {
    deleteById(slot.getDateTime());
  }
}

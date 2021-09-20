package com.tutrit.java.quickstart.service;

import com.tutrit.java.quickstart.bean.Slot;

import com.tutrit.java.quickstart.config.Storage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScheduleCalendar implements Serializable {
    private static final Map<LocalDateTime, Slot> scheduleCalendarMap = new HashMap<>();

    public void addSlot(Slot slot) {
        scheduleCalendarMap.put(slot.getDateTime(), slot);
    }

    public Map<LocalDateTime, Slot> findAll() {
        return scheduleCalendarMap;
    }

    public void removeAllSlots() {
        scheduleCalendarMap.clear();
    }

    public void saveToFile() {
        cleanStorage();
        if(!Files.exists(Storage.slotStorage)) {
            try {
                Files.createDirectories(Storage.slotStorage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for(Slot slot : scheduleCalendarMap.values()) {
            Path slotFile = Path.of(Storage.slotStorage.toString(),
                slot.getDateTime().toString() + Storage.extension);
            try {
                Files.createFile(slotFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(slotFile.toString()))) {
                oos.writeObject(slot);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadFromFile() {
        List<Path> slotsPaths = null;
        try {
            slotsPaths = Files.walk(Storage.slotStorage)
                .filter(path -> path.toString().endsWith(".slot"))
                .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Path path : slotsPaths) {
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
            scheduleCalendarMap.put(slot.getDateTime(), slot);
        }
    }

    public void cleanStorage() {
        List<Path> slotsPaths = null;
        try {
            slotsPaths = Files.walk(Storage.slotStorage)
                .filter(path -> path.toString().endsWith(".slot"))
                .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Path path : slotsPaths) {
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }
}

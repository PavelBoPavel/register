package com.tutrit.java.quickstart.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Slot is date, time and duration of some event
 * dateTime - the start moment some event
 * duration - = endTime - startTime   ////where startTime is @dateTime
 */
public class Slot implements Comparable<Slot>, Serializable {
    private LocalDateTime dateTime;
    private long duration;

    public Slot(LocalDateTime dateTime, long duration) {
        this.dateTime = dateTime;
        this.duration = duration;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Slot)) return false;
        Slot slot = (Slot) o;
        return getDuration() == slot.getDuration() && Objects.equals(getDateTime(), slot.getDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateTime(), getDuration());
    }

    @Override
    public String toString() {
        return "Slot{" +
                "dateTime=" + dateTime +
                ", duration=" + duration +
                '}';
    }

    @Override
    public int compareTo(Slot o) {
        return this.dateTime.compareTo(o.dateTime);
    }
}

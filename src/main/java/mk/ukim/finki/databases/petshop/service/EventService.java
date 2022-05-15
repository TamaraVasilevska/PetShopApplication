package mk.ukim.finki.databases.petshop.service;

import mk.ukim.finki.databases.petshop.model.CalendarEvent;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

public interface EventService{

    Iterable<CalendarEvent> findBetween(LocalDateTime start, LocalDateTime end);
    CalendarEvent create(LocalDateTime start, LocalDateTime end, String text);
    CalendarEvent changeDate(Long id, LocalDateTime start, LocalDateTime end);
    CalendarEvent putColor(Long id, String color);
}
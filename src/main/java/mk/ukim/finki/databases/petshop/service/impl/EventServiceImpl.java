package mk.ukim.finki.databases.petshop.service.impl;

import mk.ukim.finki.databases.petshop.model.CalendarEvent;
import mk.ukim.finki.databases.petshop.repository.jpa.EventRepository;
import mk.ukim.finki.databases.petshop.service.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Iterable<CalendarEvent> findBetween(LocalDateTime start, LocalDateTime end) {
        return eventRepository.findBetween(start, end);
    }

    @Override
    public CalendarEvent create(LocalDateTime start, LocalDateTime end, String text) {
        return this.eventRepository.save(new CalendarEvent(text, start, end));
    }

    @Override
    public CalendarEvent changeDate(Long id, LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public CalendarEvent putColor(Long id, String color) {
        Optional<CalendarEvent> calendarEvent = this.eventRepository.findById(id);
        CalendarEvent cal = calendarEvent.get();
        cal.setColor(color);
        return this.eventRepository.save(cal);
    }
}


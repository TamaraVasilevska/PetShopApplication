package mk.ukim.finki.databases.petshop.web.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import mk.ukim.finki.databases.petshop.model.CalendarEvent;
import mk.ukim.finki.databases.petshop.repository.jpa.EventRepository;
import mk.ukim.finki.databases.petshop.service.EventService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RestController
public class CalendarController {

    private final EventRepository eventRepository;
    private final EventService eventService;


    public CalendarController(EventRepository er, EventService eventService) {
        this.eventRepository = er;
        this.eventService = eventService;
    }


    @RequestMapping("/calendar")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("bodyContent","calendar.html");
        modelAndView.setViewName("master-template");
        return modelAndView;
    }

    @GetMapping("/api/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<CalendarEvent> events(@RequestParam("start")
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                   @RequestParam("end")
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return eventService.findBetween(start, end);
    }

    @PostMapping("/api/events/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    CalendarEvent createEvent(@RequestBody EventCreateParams params) {
        return eventService.create(params.start,params.end, params.text);
    }

    @PostMapping("/api/events/move")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    CalendarEvent moveEvent(@RequestBody EventMoveParams params) {
        return eventService.changeDate(params.id,params.start,params.end);
    }

    @PostMapping("/api/events/setColor")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    CalendarEvent setColor(@RequestBody SetColorParams params) {
        return eventService.putColor(params.id,params.color);
    }

    public static class EventCreateParams {
        public LocalDateTime start;
        public LocalDateTime end;
        public String text;
        public Long resource;
    }

    public static class EventMoveParams {
        public Long id;
        public LocalDateTime start;
        public LocalDateTime end;
        public Long resource;
    }

    public static class SetColorParams {
        public Long id;
        public String color;
    }
}
package ru.warmhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.warmhouse.dto.NotificationDto;
import ru.warmhouse.dto.TimerDto;
import ru.warmhouse.service.NotificationService;
import ru.warmhouse.service.TimerService;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired private TimerService timerService;

    @GetMapping
    public List<NotificationDto> getAll() {
        return notificationService.getAll();
    }

    @PostMapping
    public NotificationDto create(@RequestBody NotificationDto notification) {
        return notificationService.create(notification);
    }

    @PostMapping("/mark-sent/{id}")
    public void markAsSent(@PathVariable Long id) {
        notificationService.markAsSent(id);
    }

    @PostMapping("/timers")
    public TimerDto createTimer(@RequestBody TimerDto timer) {
        return timerService.save(timer);
    }
}

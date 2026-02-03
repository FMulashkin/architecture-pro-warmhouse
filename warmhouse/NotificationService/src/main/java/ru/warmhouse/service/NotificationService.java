package ru.warmhouse.service;

import ru.warmhouse.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    List<NotificationDto> getAll();

    NotificationDto create(NotificationDto notification);

    void markAsSent(Long id);
}

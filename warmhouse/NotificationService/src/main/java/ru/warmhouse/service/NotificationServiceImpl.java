package ru.warmhouse.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.warmhouse.dto.NotificationDto;
import ru.warmhouse.entity.Notification;
import ru.warmhouse.mapper.NotificationMapper;
import ru.warmhouse.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepo;
    private final SocketService socketService;
    private final NotificationMapper mapper;

    @Override
    public List<NotificationDto> getAll() {
        return notificationRepo.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public NotificationDto create(NotificationDto notification) {
        notification.setCreatedAt(LocalDateTime.now());
        notification.setSent(false);
        Notification saved = notificationRepo.save(mapper.toEntity(notification));
        socketService.sendToDevice("NOTIFICATION:" + saved.getTitle());
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public void markAsSent(Long id) {
        notificationRepo.findById(id).ifPresent(n -> {
            n.setSent(true);
            notificationRepo.save(n);
        });
    }
}

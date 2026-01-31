package ru.warmhouse.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.warmhouse.dto.NotificationDto;
import ru.warmhouse.dto.TimerDto;
import ru.warmhouse.entity.Timer;
import ru.warmhouse.mapper.TimerMapper;
import ru.warmhouse.repository.TimerRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimerServiceImpl implements TimerService {

    private final TimerMapper timerMapper;
    private final TimerRepository timerRepo;
    private final NotificationService notificationService;

    @Scheduled(fixedRate = 30000)
    @Override
    public void checkTimers() {
        LocalDateTime now = LocalDateTime.now();
        List<Timer> dueTimers = timerRepo.findByTriggerAtBeforeAndActiveTrue(now);

        for (Timer timer : dueTimers) {
            NotificationDto notification = new NotificationDto();
            notification.setTitle("Таймер сработал: " + timer.getName());
            notification.setMessage(timer.getAction());
            notification.setType("INFO");
            notification.setCreatedAt(now);

            notificationService.create(notification);
            timer.setActive(false);
            timerRepo.save(timer);
        }
    }

    @Override
    @Transactional
    public TimerDto save(TimerDto timer) {
        Timer savedTimer = timerRepo.save(timerMapper.toEntity(timer));
        return timerMapper.toDto(savedTimer);
    }
}

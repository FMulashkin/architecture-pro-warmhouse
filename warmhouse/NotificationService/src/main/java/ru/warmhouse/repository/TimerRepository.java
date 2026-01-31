package ru.warmhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.warmhouse.entity.Timer;

import java.time.LocalDateTime;
import java.util.List;

public interface TimerRepository extends JpaRepository<Timer, Long> {
    List<Timer> findByTriggerAtBeforeAndActiveTrue(LocalDateTime now);
}

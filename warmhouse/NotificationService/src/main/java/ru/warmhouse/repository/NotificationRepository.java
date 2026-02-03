package ru.warmhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.warmhouse.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}

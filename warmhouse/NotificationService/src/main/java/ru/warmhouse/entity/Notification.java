package ru.warmhouse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "message")
    private String message;
    @Column(name = "type")
    private String type; // INFO, WARNING, ERROR
    @Column(name = "sent")
    private boolean sent = false;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

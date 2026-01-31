package ru.warmhouse.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class NotificationDto implements Serializable {
    private Long id;
    private String title;
    private String message;
    private String type; // INFO, WARNING, ERROR
    private boolean sent = false;
    private LocalDateTime createdAt;
}

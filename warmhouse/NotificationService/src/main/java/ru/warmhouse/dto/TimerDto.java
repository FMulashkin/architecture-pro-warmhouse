package ru.warmhouse.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TimerDto implements Serializable {
    private Long id;
    private String name;
    private LocalDateTime triggerAt;
    private String action;
    private boolean active = true;
}

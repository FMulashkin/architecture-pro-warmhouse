package ru.warmhouse.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MetricsDto {
    private String deviceId;
    private String metricType;
    private Double value;
    private LocalDateTime timestamp;
}

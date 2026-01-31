package ru.warmhouse.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StatisticsDto {
    private String metricType;
    private Double avgValue;
    private Double minValue;
    private Double maxValue;
    private LocalDateTime calculationTime;
}

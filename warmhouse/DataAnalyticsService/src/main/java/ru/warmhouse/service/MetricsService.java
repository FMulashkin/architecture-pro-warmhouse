package ru.warmhouse.service;

import ru.warmhouse.dto.MetricsDto;

import java.util.List;

public interface MetricsService {
    List<MetricsDto> getAll();

    MetricsDto getById(Long id);

    MetricsDto create(MetricsDto dto);

    void calculateMetrics(String metricType);
}

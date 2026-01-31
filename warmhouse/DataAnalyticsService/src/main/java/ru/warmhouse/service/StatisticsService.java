package ru.warmhouse.service;

import ru.warmhouse.dto.StatisticsDto;

import java.util.List;

public interface StatisticsService {
    List<StatisticsDto> getAll();

    StatisticsDto getById(Long id);

    StatisticsDto calculateAndSaveStats(String metricType);
}

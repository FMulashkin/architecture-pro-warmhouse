package ru.warmhouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.warmhouse.dto.MetricsDto;
import ru.warmhouse.entity.Metrics;
import ru.warmhouse.mapper.MetricsMapper;
import ru.warmhouse.repository.MetricsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MetricsServiceImpl implements MetricsService {

    private final MetricsMapper metricsMapper;
    private final MetricsRepository metricsRepository;

    @Override
    public List<MetricsDto> getAll() {
        return metricsRepository.findAll().stream()
                .map(metricsMapper::toDto)
                .toList();
    }

    @Override
    public MetricsDto getById(Long id) {
        return metricsRepository.findById(id)
                .map(metricsMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Метрика не найдена"));
    }

    @Override
    public MetricsDto create(MetricsDto dto) {
        Metrics saved = metricsRepository.save(metricsMapper.toEntity(dto));
        return metricsMapper.toDto(saved);
    }

    @Override
    public void calculateMetrics(String metricType) {
        // TODO: Логика расчёта метрик по типу (например, среднее за день)
        System.out.println("Расчёт метрик для типа: " + metricType);
    }
}

package ru.warmhouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.warmhouse.dto.StatisticsDto;
import ru.warmhouse.entity.Statistics;
import ru.warmhouse.mapper.StatisticsMapper;
import ru.warmhouse.repository.StatisticsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsMapper statisticsMapper;
    private final StatisticsRepository statisticsRepository;

    @Override
    public List<StatisticsDto> getAll() {
        return statisticsRepository.findAll().stream()
                .map(statisticsMapper::toDto)
                .toList();
    }

    @Override
    public StatisticsDto getById(Long id) {
        return statisticsRepository.findById(id)
                .map(statisticsMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Статистика не найдена"));
    }

    @Override
    public StatisticsDto calculateAndSaveStats(String metricType) {
        // TODO: Расчет статистики (avg, min, max) по метрикам
        System.out.println("Расчёт статистики для: " + metricType);

        // Заглушка — имитируем сохранение
        Statistics stats = new Statistics();
        stats.setMetricType(metricType);
        stats.setAvgValue(25.5);
        stats.setMinValue(18.0);
        stats.setMaxValue(32.0);
        stats.setCalculationTime(LocalDateTime.now());

        return statisticsMapper.toDto(statisticsRepository.save(stats));
    }
}

package ru.warmhouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.warmhouse.dto.StatisticsDto;
import ru.warmhouse.service.StatisticsService;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/stats")
    public ResponseEntity<List<StatisticsDto>> getAllStats() {
        return ResponseEntity.ok(statisticsService.getAll());
    }

    @GetMapping("/stats/{id}")
    public ResponseEntity<StatisticsDto> getStats(@PathVariable Long id) {
        return ResponseEntity.ok(statisticsService.getById(id));
    }

    @PostMapping("/stats/calculate/{metricType}")
    public ResponseEntity<StatisticsDto> calculateStats(@PathVariable String metricType) {
        StatisticsDto stats = statisticsService.calculateAndSaveStats(metricType);
        return ResponseEntity.ok(stats);
    }
}

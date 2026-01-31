package ru.warmhouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.warmhouse.dto.MetricsDto;
import ru.warmhouse.service.MetricsService;

import java.util.List;

@RestController
@RequestMapping("/metrics")
@RequiredArgsConstructor
public class MetricsController {

    private final MetricsService metricsService;

    @GetMapping
    public ResponseEntity<List<MetricsDto>> getAllMetrics() {
        return ResponseEntity.ok(metricsService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetricsDto> getMetric(@PathVariable Long id) {
        return ResponseEntity.ok(metricsService.getById(id));
    }

    @PostMapping
    public ResponseEntity<MetricsDto> createMetric(@RequestBody MetricsDto dto) {
        return ResponseEntity.ok(metricsService.create(dto));
    }

    @PostMapping("/calculate/{type}")
    public ResponseEntity<String> calculateMetrics(@PathVariable String type) {
        metricsService.calculateMetrics(type);
        return ResponseEntity.ok("Расчёт метрик запущен для типа: " + type);
    }
}

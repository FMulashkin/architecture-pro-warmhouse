package ru.warmhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.warmhouse.entity.Metrics;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MetricsRepository extends JpaRepository<Metrics, Long> {
    List<Metrics> findByMetricTypeAndTimestampBetween(String metricType, LocalDateTime start, LocalDateTime end);
}

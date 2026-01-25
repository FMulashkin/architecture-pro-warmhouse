package ru.warmhouse.repository;

import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Random;

@Repository
@NoArgsConstructor
public class TemperatureRepositoryImpl implements TemperatureRepository {

    private final Random random = new Random();

    private Map<String, Integer> sensorByLocationMap;

    @PostConstruct
    public void init() {
        sensorByLocationMap = Map.of(
                "Living Room", 1,
                "Bedroom", 2,
                "Kitchen", 3);
    }

    @Override
    public Integer getSensorIdByLocation(String location) {
        return sensorByLocationMap.getOrDefault(location, 0);
    }

    @Override
    public String getLocationBySensorId(Integer sensorId) {
        return sensorByLocationMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(sensorId))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("Unknown");
    }

    @Override
    public Double getTemperatureByLocation(String location) {
        random.setSeed(location.hashCode());
        return random.nextDouble(-20, 40);
    }
}

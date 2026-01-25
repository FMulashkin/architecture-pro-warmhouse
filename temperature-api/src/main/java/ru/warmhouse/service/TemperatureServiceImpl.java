package ru.warmhouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.warmhouse.repository.TemperatureRepository;

@Service
@RequiredArgsConstructor
public class TemperatureServiceImpl implements TemperatureService {

    private final TemperatureRepository temperatureRepository;

    @Override
    public Integer getSensorIdByLocation(String location) {
        return temperatureRepository.getSensorIdByLocation(location);
    }

    @Override
    public String getLocationBySensorId(Integer sensorId) {
        return temperatureRepository.getLocationBySensorId(sensorId);
    }

    @Override
    public Double getTemperatureByLocation(String location) {
        return temperatureRepository.getTemperatureByLocation(location);
    }
}

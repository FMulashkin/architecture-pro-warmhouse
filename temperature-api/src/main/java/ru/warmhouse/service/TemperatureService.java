package ru.warmhouse.service;

public interface TemperatureService {

    Integer getSensorIdByLocation(String location);

    String getLocationBySensorId(Integer sensorId);

    Double getTemperatureByLocation(String location);
}

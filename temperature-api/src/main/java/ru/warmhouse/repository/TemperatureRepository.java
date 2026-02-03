package ru.warmhouse.repository;

public interface TemperatureRepository {

    Integer getSensorIdByLocation(String location);

    String getLocationBySensorId(Integer sensorId);

    Double getTemperatureByLocation(String location);
}

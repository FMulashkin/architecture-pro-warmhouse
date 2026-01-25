package ru.warmhouse.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TemperatureResponse {
    private String location;
    private Double temperature;
    private Integer sensorId;
}

package ru.warmhouse.dto;

import lombok.Data;

@Data
public class ThermostatDto {
    private Long id;
    private String name;
    private Double currentTemperature;
    private Double targetTemperature;
    private boolean enabled;
}
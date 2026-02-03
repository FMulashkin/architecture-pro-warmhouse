package ru.warmhouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.warmhouse.dto.TemperatureResponse;
import ru.warmhouse.service.TemperatureService;

@RestController
@RequiredArgsConstructor
public class TemperatureController {

    private final TemperatureService temperatureService;

    @GetMapping("/temperature")
    public TemperatureResponse getTemperature(@RequestParam String location) {
        return TemperatureResponse.builder()
                .location(location)
                .temperature(temperatureService.getTemperatureByLocation(location))
                .sensorId(temperatureService.getSensorIdByLocation(location))
                .build();
    }
}

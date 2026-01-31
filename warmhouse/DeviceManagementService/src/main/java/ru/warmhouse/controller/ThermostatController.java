package ru.warmhouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.warmhouse.dto.ThermostatDto;
import ru.warmhouse.service.ThermostatService;

import java.util.List;

@RestController
@RequestMapping("/thermostat")
@RequiredArgsConstructor
public class ThermostatController {

    private final ThermostatService thermostatService;

    @GetMapping("/thermostats")
    public ResponseEntity<List<ThermostatDto>> getAllThermostats() {
        return ResponseEntity.ok(thermostatService.getAll());
    }

    @GetMapping("/thermostats/{id}")
    public ResponseEntity<ThermostatDto> getThermostat(@PathVariable Long id) {
        return ResponseEntity.ok(thermostatService.getById(id));
    }

    @PostMapping("/thermostats")
    public ResponseEntity<ThermostatDto> createThermostat(@RequestBody ThermostatDto dto) {
        return ResponseEntity.ok(thermostatService.create(dto));
    }

    @PutMapping("/thermostats/{id}")
    public ResponseEntity<ThermostatDto> updateThermostat(@PathVariable Long id, @RequestBody ThermostatDto dto) {
        return ResponseEntity.ok(thermostatService.update(id, dto));
    }

    @DeleteMapping("/thermostats/{id}")
    public ResponseEntity<Void> deleteThermostat(@PathVariable Long id) {
        thermostatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

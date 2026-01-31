package ru.warmhouse.service;

import ru.warmhouse.dto.ThermostatDto;

import java.util.List;

public interface ThermostatService {
    List<ThermostatDto> getAll();

    ThermostatDto getById(Long id);

    ThermostatDto create(ThermostatDto dto);

    ThermostatDto update(Long id, ThermostatDto dto);

    void delete(Long id);
}

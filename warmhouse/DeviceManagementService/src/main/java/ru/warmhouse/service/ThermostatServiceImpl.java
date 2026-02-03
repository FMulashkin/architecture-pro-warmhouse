package ru.warmhouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.warmhouse.dto.ThermostatDto;
import ru.warmhouse.entity.Thermostat;
import ru.warmhouse.mapper.ThermostatMapper;
import ru.warmhouse.repository.ThermostatRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThermostatServiceImpl implements ThermostatService {

    private final ThermostatMapper thermostatMapper;
    private final ThermostatRepository thermostatRepository;

    @Override
    public List<ThermostatDto> getAll() {
        return thermostatRepository.findAll().stream()
                .map(thermostatMapper::toDto)
                .toList();
    }

    @Override
    public ThermostatDto getById(Long id) {
        return thermostatRepository.findById(id)
                .map(thermostatMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Термостат не найден"));
    }

    @Override
    public ThermostatDto create(ThermostatDto dto) {
        Thermostat saved = thermostatRepository.save(thermostatMapper.toEntity(dto));
        return thermostatMapper.toDto(saved);
    }

    @Override
    public ThermostatDto update(Long id, ThermostatDto dto) {
        ThermostatDto thermostat = getById(id);
        thermostat.setName(dto.getName());
        thermostat.setCurrentTemperature(dto.getCurrentTemperature());
        thermostat.setTargetTemperature(dto.getTargetTemperature());
        thermostat.setEnabled(dto.isEnabled());
        Thermostat saved = thermostatRepository.save(thermostatMapper.toEntity(thermostat));
        return thermostatMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        thermostatRepository.deleteById(id);
    }
}

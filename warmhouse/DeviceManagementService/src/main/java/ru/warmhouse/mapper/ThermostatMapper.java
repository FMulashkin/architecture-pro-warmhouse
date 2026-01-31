package ru.warmhouse.mapper;

import org.mapstruct.Mapper;
import ru.warmhouse.dto.ThermostatDto;
import ru.warmhouse.entity.Thermostat;

@Mapper(componentModel = "spring")
public interface ThermostatMapper {

    ThermostatDto toDto(Thermostat source);

    Thermostat toEntity(ThermostatDto source);
}

package ru.warmhouse.mapper;

import org.mapstruct.Mapper;
import ru.warmhouse.dto.TimerDto;
import ru.warmhouse.entity.Timer;

@Mapper(componentModel = "spring")
public interface TimerMapper {

    Timer toEntity(TimerDto source);

    TimerDto toDto(Timer source);
}

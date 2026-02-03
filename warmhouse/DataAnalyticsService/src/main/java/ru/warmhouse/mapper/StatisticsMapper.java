package ru.warmhouse.mapper;

import org.mapstruct.Mapper;
import ru.warmhouse.dto.StatisticsDto;
import ru.warmhouse.entity.Statistics;

@Mapper(componentModel = "spring")
public interface StatisticsMapper {

    StatisticsDto toDto(Statistics source);

    Statistics toEntity(StatisticsDto source);
}

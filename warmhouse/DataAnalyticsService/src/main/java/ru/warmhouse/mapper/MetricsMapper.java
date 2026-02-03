package ru.warmhouse.mapper;

import org.mapstruct.Mapper;
import ru.warmhouse.dto.MetricsDto;
import ru.warmhouse.entity.Metrics;

@Mapper(componentModel = "spring")
public interface MetricsMapper {

    Metrics toEntity(MetricsDto source);

    MetricsDto toDto(Metrics source);
}

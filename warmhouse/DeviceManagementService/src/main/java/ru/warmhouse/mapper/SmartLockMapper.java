package ru.warmhouse.mapper;

import org.mapstruct.Mapper;
import ru.warmhouse.dto.SmartLockDto;
import ru.warmhouse.entity.SmartLock;

@Mapper(componentModel = "spring")
public interface SmartLockMapper {

    SmartLockDto toDto(SmartLock source);

    SmartLock toEntity(SmartLockDto source);
}

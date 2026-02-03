package ru.warmhouse.mapper;

import org.mapstruct.Mapper;
import ru.warmhouse.dto.CameraDto;
import ru.warmhouse.entity.Camera;

@Mapper(componentModel = "spring")
public interface CameraMapper {

    CameraDto toDto(Camera source);

    Camera toEntity(CameraDto source);
}

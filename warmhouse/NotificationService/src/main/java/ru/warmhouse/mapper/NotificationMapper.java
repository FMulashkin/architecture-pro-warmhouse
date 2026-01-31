package ru.warmhouse.mapper;

import org.mapstruct.Mapper;
import ru.warmhouse.dto.NotificationDto;
import ru.warmhouse.entity.Notification;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationDto toDto(Notification source);

    Notification toEntity(NotificationDto source);
}

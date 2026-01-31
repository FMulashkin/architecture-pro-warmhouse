package ru.warmhouse.mapper;

import org.mapstruct.Mapper;
import ru.warmhouse.dto.UserDto;
import ru.warmhouse.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User source);

    User toEntity(UserDto source);
}

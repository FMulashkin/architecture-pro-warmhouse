package ru.warmhouse.service;

import ru.warmhouse.dto.UserDto;

public interface UserService {

    void register(UserDto user);

    boolean authenticate(String email, String password);
}

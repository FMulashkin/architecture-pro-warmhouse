package ru.warmhouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.warmhouse.dto.UserDto;
import ru.warmhouse.mapper.UserMapper;
import ru.warmhouse.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void register(UserDto user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует");
        }
        userRepository.save(userMapper.toEntity(user)); // В реальности используй BCrypt
    }

    @Override
    public boolean authenticate(String email, String password) {
        return userRepository.findByEmail(email)
                .map(user -> user.getPassword().equals(password)) // В реальности используй BCrypt
                .orElse(false);
    }
}

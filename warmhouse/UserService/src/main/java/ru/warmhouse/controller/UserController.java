package ru.warmhouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.warmhouse.dto.UserDto;
import ru.warmhouse.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestBody UserDto user) {
        try {
            userService.register(user);
            return ResponseEntity.ok("Пользователь успешно зарегистрирован");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка регистрации: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto user) {
        return userService.authenticate(user.getEmail(), user.getPassword())
                ? ResponseEntity.ok("Авторизация успешна")
                : ResponseEntity.status(401).body("Неверный email или пароль");
    }

    @GetMapping("/auth")
    public ResponseEntity<String> auth() {
        return ResponseEntity.ok("Доступ разрешён для авторизованного пользователя");
    }
}

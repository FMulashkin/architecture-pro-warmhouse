package ru.warmhouse.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private Long id;

    private String email;

    private String password;
}

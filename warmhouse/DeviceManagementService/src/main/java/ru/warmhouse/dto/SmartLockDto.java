package ru.warmhouse.dto;

import lombok.Data;

@Data
public class SmartLockDto {
    private Long id;
    private String name;
    private boolean locked;
    private String location;
}

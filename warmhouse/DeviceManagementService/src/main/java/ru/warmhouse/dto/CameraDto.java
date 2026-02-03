package ru.warmhouse.dto;

import lombok.Data;

@Data
public class CameraDto {
    private Long id;
    private String name;
    private String ipAddress;
    private boolean recording;
    private boolean motionDetectionEnabled;
}

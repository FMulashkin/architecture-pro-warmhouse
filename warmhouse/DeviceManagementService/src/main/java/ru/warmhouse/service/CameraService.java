package ru.warmhouse.service;

import ru.warmhouse.dto.CameraDto;

import java.util.List;

public interface CameraService {
    List<CameraDto> getAll();

    CameraDto getById(Long id);

    CameraDto create(CameraDto camera);

    CameraDto update(Long id, CameraDto updatedCamera);

    void delete(Long id);
}

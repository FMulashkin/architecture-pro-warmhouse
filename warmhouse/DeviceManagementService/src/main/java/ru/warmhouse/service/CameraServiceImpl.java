package ru.warmhouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.warmhouse.dto.CameraDto;
import ru.warmhouse.entity.Camera;
import ru.warmhouse.mapper.CameraMapper;
import ru.warmhouse.repository.CameraRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CameraServiceImpl implements CameraService {

    private final CameraMapper cameraMapper;
    private final CameraRepository cameraRepository;

    @Override
    public List<CameraDto> getAll() {
        return cameraRepository.findAll().stream()
                .map(cameraMapper::toDto)
                .toList();
    }

    @Override
    public CameraDto getById(Long id) {
        return cameraRepository.findById(id)
                .map(cameraMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Камера не найдена"));
    }

    @Override
    public CameraDto create(CameraDto camera) {
        Camera saved = cameraRepository.save(cameraMapper.toEntity(camera));
        return cameraMapper.toDto(saved);
    }

    @Override
    public CameraDto update(Long id, CameraDto updatedCamera) {
        CameraDto camera = getById(id);
        camera.setName(updatedCamera.getName());
        camera.setIpAddress(updatedCamera.getIpAddress());
        camera.setRecording(updatedCamera.isRecording());
        camera.setMotionDetectionEnabled(updatedCamera.isMotionDetectionEnabled());
        Camera saved = cameraRepository.save(cameraMapper.toEntity(camera));
        return cameraMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        cameraRepository.deleteById(id);
    }
}

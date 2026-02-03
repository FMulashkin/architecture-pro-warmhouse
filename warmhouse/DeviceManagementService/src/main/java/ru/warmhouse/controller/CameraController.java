package ru.warmhouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.warmhouse.dto.CameraDto;
import ru.warmhouse.service.CameraService;

import java.util.List;

@RestController
@RequestMapping("/camera")
@RequiredArgsConstructor
public class CameraController {

    private final CameraService cameraService;

    @GetMapping("/cameras")
    public ResponseEntity<List<CameraDto>> getAllCameras() {
        return ResponseEntity.ok(cameraService.getAll());
    }

    @GetMapping("/cameras/{id}")
    public ResponseEntity<CameraDto> getCamera(@PathVariable Long id) {
        return ResponseEntity.ok(cameraService.getById(id));
    }

    @PostMapping("/cameras")
    public ResponseEntity<CameraDto> createCamera(@RequestBody CameraDto camera) {
        return ResponseEntity.ok(cameraService.create(camera));
    }

    @PutMapping("/cameras/{id}")
    public ResponseEntity<CameraDto> updateCamera(@PathVariable Long id, @RequestBody CameraDto camera) {
        return ResponseEntity.ok(cameraService.update(id, camera));
    }

    @DeleteMapping("/cameras/{id}")
    public ResponseEntity<Void> deleteCamera(@PathVariable Long id) {
        cameraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

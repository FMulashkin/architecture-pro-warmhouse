package ru.warmhouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.warmhouse.dto.SmartLockDto;
import ru.warmhouse.service.SmartLockService;

import java.util.List;

@RestController
@RequestMapping("/smart-lock")
@RequiredArgsConstructor
public class SmartLockController {

    private final SmartLockService smartLockService;

    @GetMapping("/locks")
    public ResponseEntity<List<SmartLockDto>> getAllLocks() {
        return ResponseEntity.ok(smartLockService.getAll());
    }

    @GetMapping("/locks/{id}")
    public ResponseEntity<SmartLockDto> getLock(@PathVariable Long id) {
        return ResponseEntity.ok(smartLockService.getById(id));
    }

    @PostMapping("/locks")
    public ResponseEntity<SmartLockDto> createLock(@RequestBody SmartLockDto lock) {
        return ResponseEntity.ok(smartLockService.create(lock));
    }

    @PutMapping("/locks/{id}")
    public ResponseEntity<SmartLockDto> updateLock(@PathVariable Long id, @RequestBody SmartLockDto lock) {
        return ResponseEntity.ok(smartLockService.update(id, lock));
    }

    @DeleteMapping("/locks/{id}")
    public ResponseEntity<Void> deleteLock(@PathVariable Long id) {
        smartLockService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

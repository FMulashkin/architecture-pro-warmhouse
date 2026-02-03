package ru.warmhouse.service;

import ru.warmhouse.dto.SmartLockDto;

import java.util.List;

public interface SmartLockService {
    List<SmartLockDto> getAll();

    SmartLockDto getById(Long id);

    SmartLockDto create(SmartLockDto lock);

    SmartLockDto update(Long id, SmartLockDto updatedLock);

    void delete(Long id);
}

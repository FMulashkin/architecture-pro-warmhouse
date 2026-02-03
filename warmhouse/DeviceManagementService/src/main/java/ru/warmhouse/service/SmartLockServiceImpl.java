package ru.warmhouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.warmhouse.dto.SmartLockDto;
import ru.warmhouse.entity.SmartLock;
import ru.warmhouse.mapper.SmartLockMapper;
import ru.warmhouse.repository.SmartLockRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SmartLockServiceImpl implements SmartLockService {

    private final SmartLockMapper smartLockMapper;
    private final SmartLockRepository smartLockRepository;

    @Override
    public List<SmartLockDto> getAll() {
        return smartLockRepository.findAll().stream()
                .map(smartLockMapper::toDto)
                .toList();
    }

    @Override
    public SmartLockDto getById(Long id) {
        return smartLockRepository.findById(id)
                .map(smartLockMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Замок не найден"));
    }

    @Override
    public SmartLockDto create(SmartLockDto lock) {
        SmartLock saved = smartLockRepository.save(smartLockMapper.toEntity(lock));
        return smartLockMapper.toDto(saved);
    }

    @Override
    public SmartLockDto update(Long id, SmartLockDto updatedLock) {
        SmartLockDto lock = getById(id);
        lock.setName(updatedLock.getName());
        lock.setLocked(updatedLock.isLocked());
        lock.setLocation(updatedLock.getLocation());
        SmartLock saved = smartLockRepository.save(smartLockMapper.toEntity(lock));
        return smartLockMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        smartLockRepository.deleteById(id);
    }
}

package ru.warmhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.warmhouse.entity.Camera;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Long> {}

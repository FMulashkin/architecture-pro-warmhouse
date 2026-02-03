package ru.warmhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.warmhouse.entity.SmartLock;

@Repository
public interface SmartLockRepository extends JpaRepository<SmartLock, Long> {}

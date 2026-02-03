package ru.warmhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.warmhouse.entity.Thermostat;

@Repository
public interface ThermostatRepository extends JpaRepository<Thermostat, Long> {}

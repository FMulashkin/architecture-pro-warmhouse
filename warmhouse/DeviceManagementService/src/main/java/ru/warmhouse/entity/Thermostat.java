package ru.warmhouse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "thermostats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Thermostat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "current_temperature")
    private Double currentTemperature;
    @Column(name = "target_temperature")
    private Double targetTemperature;
    @Column(name = "enabled")
    private boolean enabled;
}

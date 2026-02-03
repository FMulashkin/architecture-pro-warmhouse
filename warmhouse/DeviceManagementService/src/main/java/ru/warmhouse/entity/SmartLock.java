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
@Table(name = "smart_locks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmartLock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "is_locked", nullable = false)
    private boolean locked;
    @Column(name = "location")
    private String location; // например, "Front Door", "Garage"
}

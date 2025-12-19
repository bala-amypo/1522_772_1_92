package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "hotspot_zones")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class HotspotZone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String zoneName;

    @NotNull
    private Double centerLat;

    @NotNull
    private Double centerLong;

    @NotBlank
    private String severityLevel; // LOW, MEDIUM, HIGH

    @PrePersist
    public void validateCoordinates() {
        if (centerLat < -90 || centerLat > 90) {
            throw new IllegalArgumentException("latitude out of range");
        }
        if (centerLong < -180 || centerLong > 180) {
            throw new IllegalArgumentException("longitude out of range");
        }
    }
}

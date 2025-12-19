package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "crime_reports")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class CrimeReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String crimeType;

    private String description;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private LocalDateTime occurredAt;

    @PrePersist
    public void validate() {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("latitude out of range");
        }
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("longitude out of range");
        }
        if (occurredAt.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("occurredAt cannot be in the future");
        }
    }
}

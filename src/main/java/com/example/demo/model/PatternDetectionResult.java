package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "pattern_detection_results")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class PatternDetectionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "zone_id", nullable = false)
    private HotspotZone zone;

    private LocalDate analysisDate;

    @Min(0)
    private Integer crimeCount;

    @NotBlank
    private String detectedPattern;
}

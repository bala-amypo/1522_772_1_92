package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "pattern_detection_result")
public class PatternDetectionResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "Crime count cannot be negative")
    private int crimeCount;

    @NotBlank(message = "Detected pattern cannot be empty")
    private String detectedPattern;

    @NotNull(message = "Analysis date is required")
    private LocalDate analysisDate;

    @NotNull(message = "Hotspot zone must be provided")
    @ManyToOne
    private HotspotZoneEntity zone;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getCrimeCount() { return crimeCount; }
    public void setCrimeCount(int crimeCount) { this.crimeCount = crimeCount; }

    public String getDetectedPattern() { return detectedPattern; }
    public void setDetectedPattern(String detectedPattern) { this.detectedPattern = detectedPattern; }

    public LocalDate getAnalysisDate() { return analysisDate; }
    public void setAnalysisDate(LocalDate analysisDate) { this.analysisDate = analysisDate; }

    public HotspotZoneEntity getZone() { return zone; }
    public void setZone(HotspotZoneEntity zone) { this.zone = zone; }
}

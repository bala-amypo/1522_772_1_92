package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class PatternDetectionResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int crimeCount;
    private String detectedPattern;
    private LocalDate analysisDate;

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

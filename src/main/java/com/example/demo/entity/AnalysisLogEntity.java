package com.example.collectiondp.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PatternDetectionResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private int crimeCount;
    private String detectedPattern;
    private LocalDate analysisDate;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getCrimeCount() {
        return crimeCount;
    }
    public void setCrimeCount(int crimeCount) {
        this.crimeCount = crimeCount;
    }
    public String getDetectedPattern() {
        return detectedPattern;
    }
    public void setDetectedPattern(String detectedPattern) {
        this.detectedPattern = detectedPattern;
    }
    public LocalDate getAnalysisDate() {
        return analysisDate;
    }
    public void setAnalysisDate(LocalDate analysisDate) {
        this.analysisDate = analysisDate;
    }
    public PatternDetectionResultEntity() {
    }
    public PatternDetectionResultEntity(Long id, int crimeCount, String detectedPattern, LocalDate analysisDate) {
        this.id = id;
        this.crimeCount = crimeCount;
        this.detectedPattern = detectedPattern;
        this.analysisDate = analysisDate;
    }

    
}
/*package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pattern_detection_results")
public class PatternDetectionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private HotspotZone zone;

    private LocalDate analysisDate;

    private Integer crimeCount;

    private String detectedPattern;

    public PatternDetectionResult() {
    }

    public PatternDetectionResult(HotspotZone zone, LocalDate analysisDate,
                                  Integer crimeCount, String detectedPattern) {
        this.zone = zone;
        this.analysisDate = analysisDate;
        this.crimeCount = crimeCount;
        this.detectedPattern = detectedPattern;
    }


    public Long getId() {
        return id;
    }

    public HotspotZone getZone() {
        return zone;
    }

    public void setZone(HotspotZone zone) {
        this.zone = zone;
    }

    public LocalDate getAnalysisDate() {
        return analysisDate;
    }

    public void setAnalysisDate(LocalDate analysisDate) {
        this.analysisDate = analysisDate;
    }

    public Integer getCrimeCount() {
        return crimeCount;
    }

    public void setCrimeCount(Integer crimeCount) {
        this.crimeCount = crimeCount;
    }

    public String getDetectedPattern() {
        return detectedPattern;
    }

    public void setDetectedPattern(String detectedPattern) {
        this.detectedPattern = detectedPattern;
    }
}*/
package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "pattern_detection_results")
public class PatternDetectionResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "zone_id", nullable = false)
    private HotspotZone zone;
    
    @NotNull(message = "Analysis date is required")
    @Column(name = "analysis_date")
    private LocalDate analysisDate;
    
    @NotNull(message = "Crime count is required")
    @Min(value = 0, message = "Crime count must be non-negative")
    @Column(name = "crime_count")
    private Integer crimeCount;
    
    @NotBlank(message = "Detected pattern is required")
    @Column(name = "detected_pattern")
    private String detectedPattern;
    
    public PatternDetectionResult() {
    }
    
    public PatternDetectionResult(HotspotZone zone, LocalDate analysisDate, 
                                  Integer crimeCount, String detectedPattern) {
        this.zone = zone;
        this.analysisDate = analysisDate;
        this.crimeCount = crimeCount;
        this.detectedPattern = detectedPattern;
    }
    
    @PrePersist
    protected void onCreate() {
        if (this.analysisDate == null) {
            this.analysisDate = LocalDate.now();
        }
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public HotspotZone getZone() {
        return zone;
    }
    
    public void setZone(HotspotZone zone) {
        this.zone = zone;
    }
    
    public LocalDate getAnalysisDate() {
        return analysisDate;
    }
    
    public void setAnalysisDate(LocalDate analysisDate) {
        this.analysisDate = analysisDate;
    }
    
    public Integer getCrimeCount() {
        return crimeCount;
    }
    
    public void setCrimeCount(Integer crimeCount) {
        this.crimeCount = crimeCount;
    }
    
    public String getDetectedPattern() {
        return detectedPattern;
    }
    
    public void setDetectedPattern(String detectedPattern) {
        this.detectedPattern = detectedPattern;
    }
}
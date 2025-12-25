package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "hotspot_zones")
public class HotspotZone {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Zone name is required")
    @Size(min = 3, max = 100, message = "Zone name must be between 3 and 100 characters")
    @Column(name = "zone_name", unique = true)
    private String zoneName;
    
    @NotNull(message = "Center latitude is required")
    @Min(value = -90, message = "Center latitude must be between -90 and 90")
    @Max(value = 90, message = "Center latitude must be between -90 and 90")
    @Column(name = "center_lat")
    private Double centerLat;
    
    @NotNull(message = "Center longitude is required")
    @Min(value = -180, message = "Center longitude must be between -180 and 180")
    @Max(value = 180, message = "Center longitude must be between -180 and 180")
    @Column(name = "center_long")
    private Double centerLong;
    
    @NotBlank(message = "Severity level is required")
    @Column(name = "severity_level")
    private String severityLevel = "LOW";
    
    @Positive(message = "Radius must be positive")
    @Column(name = "radius_meters")
    private Double radiusMeters = 0.1;
    
    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    private List<PatternDetectionResult> detectionResults;
    
    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    private List<AnalysisLog> analysisLogs;
    
    public HotspotZone() {
    }
    
    public HotspotZone(String zoneName, Double centerLat, Double centerLong, 
                       String severityLevel) {
        this.zoneName = zoneName;
        this.centerLat = centerLat;
        this.centerLong = centerLong;
        this.severityLevel = severityLevel != null ? severityLevel : "LOW";
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getZoneName() {
        return zoneName;
    }
    
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }
    
    public Double getCenterLat() {
        return centerLat;
    }
    
    public void setCenterLat(Double centerLat) {
        this.centerLat = centerLat;
    }
    
    public Double getCenterLong() {
        return centerLong;
    }
    
    public void setCenterLong(Double centerLong) {
        this.centerLong = centerLong;
    }
    
    public String getSeverityLevel() {
        return severityLevel;
    }
    
    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }
    
    public Double getRadiusMeters() {
        return radiusMeters;
    }
    
    public void setRadiusMeters(Double radiusMeters) {
        this.radiusMeters = radiusMeters;
    }
    
    public List<PatternDetectionResult> getDetectionResults() {
        return detectionResults;
    }
    
    public void setDetectionResults(List<PatternDetectionResult> detectionResults) {
        this.detectionResults = detectionResults;
    }
    
    public List<AnalysisLog> getAnalysisLogs() {
        return analysisLogs;
    }
    
    public void setAnalysisLogs(List<AnalysisLog> analysisLogs) {
        this.analysisLogs = analysisLogs;
    }
}
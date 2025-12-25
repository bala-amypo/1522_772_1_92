package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "analysis_logs")
public class AnalysisLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private LocalDateTime loggedAt;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private HotspotZone zone;

    public AnalysisLog() {
        this.loggedAt = LocalDateTime.now();
    }

    public AnalysisLog(String message, HotspotZone zone) {
        this.message = message;
        this.zone = zone;
        this.loggedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.loggedAt = LocalDateTime.now();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public HotspotZone getZone() {
        return zone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setZone(HotspotZone zone) {
        this.zone = zone;
    }
}

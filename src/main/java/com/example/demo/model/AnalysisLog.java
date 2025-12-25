/*package com.example.demo.model;

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
    }

    public AnalysisLog(String message, HotspotZone zone) {
        this.message = message;
        this.zone = zone;
    }

    @PrePersist
    public void prePersist() {
        this.loggedAt = LocalDateTime.now();
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public HotspotZone getZone() {
        return zone;
    }

    public void setZone(HotspotZone zone) {
        this.zone = zone;
    }
}*/

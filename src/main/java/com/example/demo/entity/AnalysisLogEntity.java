package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "analysis_log")
public class AnalysisLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Log message cannot be empty")
    @Size(max = 255, message = "Message too long")
    private String message;

    @NotNull(message = "Hotspot zone must be provided")
    @ManyToOne
    private HotspotZoneEntity zone;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public HotspotZoneEntity getZone() { return zone; }
    public void setZone(HotspotZoneEntity zone) { this.zone = zone; }
}

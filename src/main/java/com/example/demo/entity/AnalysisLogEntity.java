package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class AnalysisLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    private HotspotZoneEntity zone;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public HotspotZoneEntity getZone() { return zone; }
    public void setZone(HotspotZoneEntity zone) { this.zone = zone; }
}

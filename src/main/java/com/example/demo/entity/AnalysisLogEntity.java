package com.example.collectiondp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class AnalysisLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String message;

    @ManyToOne
    private HotspotZoneEntity zone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HotspotZoneEntity getZone() {
        return zone;
    }

    public void setZone(HotspotZoneEntity zone) {
        this.zone = zone;
    }

    public AnalysisLogEntity() {
    }

    public AnalysisLogEntity(Long id, String message, HotspotZoneEntity zone) {
        this.id = id;
        this.message = message;
        this.zone = zone;
    }

    
}
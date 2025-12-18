package com.example.collectiondp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HotspotZoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String zoneName;
    private double centerLat;
    private double centerLong;
    private String severityLevel;
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
    public double getCenterLat() {
        return centerLat;
    }
    public void setCenterLat(double centerLat) {
        this.centerLat = centerLat;
    }
    public double getCenterLong() {
        return centerLong;
    }
    public void setCenterLong(double centerLong) {
        this.centerLong = centerLong;
    }
    public String getSeverityLevel() {
        return severityLevel;
    }
    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }
    public HotspotZoneEntity() {
    }
    public HotspotZoneEntity(Long id, String zoneName, double centerLat, double centerLong, String severityLevel) {
        this.id = id;
        this.zoneName = zoneName;
        this.centerLat = centerLat;
        this.centerLong = centerLong;
        this.severityLevel = severityLevel;
    }

}
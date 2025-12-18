package com.example.collectiondp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CrimeReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private double latitude;
    private double longitude;
    private String crimeType;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public String getCrimeType() {
        return crimeType;
    }
    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
    }
    public CrimeReportEntity() {
    }
    public CrimeReportEntity(Long id, double latitude, double longitude, String crimeType) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.crimeType = crimeType;
    }

    
}
package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "crime_report")
public class CrimeReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.0", message = "Latitude must be ≥ -90")
    @DecimalMax(value = "90.0", message = "Latitude must be ≤ 90")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.0", message = "Longitude must be ≥ -180")
    @DecimalMax(value = "180.0", message = "Longitude must be ≤ 180")
    private Double longitude;

    @NotBlank(message = "Crime type cannot be empty")
    private String crimeType;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getCrimeType() { return crimeType; }
    public void setCrimeType(String crimeType) { this.crimeType = crimeType; }
}

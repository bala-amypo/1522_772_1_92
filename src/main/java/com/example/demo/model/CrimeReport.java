/*package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "crime_reports")
public class CrimeReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String crimeType;
    private String description;
    private Double latitude;
    private Double longitude;
    private LocalDateTime occurredAt;

    public CrimeReport() {
    }

    public CrimeReport(String crimeType, String description,
                       Double latitude, Double longitude,
                       LocalDateTime occurredAt) {
        this.crimeType = crimeType;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.occurredAt = occurredAt;
    }

    

    public Long getId() {
        return id;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
    }

    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public Double getLatitude() {
        return latitude;
    }
 
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
 
    public Double getLongitude() {
        return longitude;
    }
 
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
 
    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }
 
    public void setOccurredAt(LocalDateTime occurredAt) {
        this.occurredAt = occurredAt;
    }
}*/

package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "crime_reports")
public class CrimeReport {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Crime type is required")
    @Size(max = 100, message = "Crime type must not exceed 100 characters")
    @Column(name = "crime_type")
    private String crimeType;
    
    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
    
    @NotNull(message = "Latitude is required")
    @Min(value = -90, message = "Latitude must be between -90 and 90")
    @Max(value = 90, message = "Latitude must be between -90 and 90")
    private Double latitude;
    
    @NotNull(message = "Longitude is required")
    @Min(value = -180, message = "Longitude must be between -180 and 180")
    @Max(value = 180, message = "Longitude must be between -180 and 180")
    private Double longitude;
    
    @NotNull(message = "Occurred at timestamp is required")
    @Column(name = "occurred_at")
    private LocalDateTime occurredAt;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    public CrimeReport() {
    }
    
    public CrimeReport(String crimeType, String description, Double latitude, 
                       Double longitude, LocalDateTime occurredAt) {
        this.crimeType = crimeType;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.occurredAt = occurredAt;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCrimeType() {
        return crimeType;
    }
    
    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Double getLatitude() {
        return latitude;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public Double getLongitude() {
        return longitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    
    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }
    
    public void setOccurredAt(LocalDateTime occurredAt) {
        this.occurredAt = occurredAt;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
}

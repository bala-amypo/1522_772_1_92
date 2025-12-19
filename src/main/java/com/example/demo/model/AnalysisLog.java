package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "analysis_logs")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class AnalysisLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String message;

    private LocalDateTime loggedAt;

    @ManyToOne
    @JoinColumn(name = "zone_id", nullable = false)
    private HotspotZone zone;

    @PrePersist
    public void prePersist() {
        this.loggedAt = LocalDateTime.now();
    }
}

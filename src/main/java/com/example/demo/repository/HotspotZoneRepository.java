package com.example.demo.repository;

import com.example.demo.entity.HotspotZoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotspotZoneRepository
        extends JpaRepository<HotspotZoneEntity, Long> {
}

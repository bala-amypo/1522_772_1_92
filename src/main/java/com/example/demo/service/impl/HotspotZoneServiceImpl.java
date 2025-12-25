/*package com.example.demo.service.impl;

import com.example.demo.model.HotspotZone;
import com.example.demo.repository.HotspotZoneRepository;
import com.example.demo.service.HotspotZoneService;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HotspotZoneServiceImpl implements HotspotZoneService {

    private final HotspotZoneRepository zoneRepository;

    public HotspotZoneServiceImpl(HotspotZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public HotspotZone addZone(HotspotZone zone) {

        if (zoneRepository.existsByZoneName(zone.getZoneName())) {
            throw new IllegalArgumentException("zone already exists");
        }

        if (zone.getCenterLat() < -90 || zone.getCenterLat() > 90) {
            throw new IllegalArgumentException("latitude out of range");
        }

        if (zone.getCenterLong() < -180 || zone.getCenterLong() > 180) {
            throw new IllegalArgumentException("longitude out of range");
        }

        if (zone.getSeverityLevel() == null || zone.getSeverityLevel().isBlank()) {
            zone.setSeverityLevel("LOW");
        }

        return zoneRepository.save(zone);
    }

    @Override
    public List<HotspotZone> getAllZones() {
        return zoneRepository.findAll();
    }
}
*/
package com.example.demo.service.impl;

import com.example.demo.model.HotspotZone;
import com.example.demo.repository.HotspotZoneRepository;
import com.example.demo.service.HotspotZoneService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HotspotZoneServiceImpl implements HotspotZoneService {
    
    private final HotspotZoneRepository zoneRepository;
    
    public HotspotZoneServiceImpl(HotspotZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }
    
    @Override
    public HotspotZone addZone(HotspotZone zone) throws Exception {
        // Check for duplicate zone name
        if (zoneRepository.existsByZoneName(zone.getZoneName())) {
            throw new IllegalArgumentException("Zone name already exists");
        }
        
        // Validate coordinates
        if (zone.getCenterLat() == null || zone.getCenterLat() < -90 || 
            zone.getCenterLat() > 90) {
            throw new IllegalArgumentException(
                "Validation failed: Center latitude must be between -90 and 90");
        }
        
        if (zone.getCenterLong() == null || zone.getCenterLong() < -180 || 
            zone.getCenterLong() > 180) {
            throw new IllegalArgumentException(
                "Validation failed: Center longitude must be between -180 and 180");
        }
        
        // Set default severity if not provided
        if (zone.getSeverityLevel() == null || zone.getSeverityLevel().isEmpty()) {
            zone.setSeverityLevel("LOW");
        }
        
        return zoneRepository.save(zone);
    }
    
    @Override
    public List<HotspotZone> getAllZones() {
        return zoneRepository.findAll();
    }
}
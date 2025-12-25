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
    public HotspotZone addZone(HotspotZone zone) {

        if (zoneRepository.existsByZoneName(zone.getZoneName())) {
            throw new IllegalArgumentException("zone already exists");
        }

        if (zone.getCenterLat() < -90 || zone.getCenterLat() > 90) {
            throw new IllegalArgumentException("latitude invalid");
        }

        if (zone.getCenterLong() < -180 || zone.getCenterLong() > 180) {
            throw new IllegalArgumentException("longitude invalid");
        }

        return zoneRepository.save(zone);
    }

    @Override
    public List<HotspotZone> getAllZones() {
        return zoneRepository.findAll();
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.HotspotZoneEntity;
import com.example.demo.repository.HotspotZoneRepository;
import com.example.demo.service.HotspotZoneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotspotZoneServiceImpl implements HotspotZoneService {

    private final HotspotZoneRepository zoneRepo;

    public HotspotZoneServiceImpl(HotspotZoneRepository zoneRepo) {
        this.zoneRepo = zoneRepo;
    }

    @Override
    public HotspotZoneEntity save(HotspotZoneEntity zone) {
        return zoneRepo.save(zone);
    }

    @Override
    public List<HotspotZoneEntity> getAll() {
        return zoneRepo.findAll();
    }

    @Override
    public HotspotZoneEntity getById(Long id) {
        return zoneRepo.findById(id).orElse(null);
    }
}

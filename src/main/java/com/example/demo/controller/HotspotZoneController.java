package com.example.demo.controller;

import com.example.demo.entity.HotspotZoneEntity;
import com.example.demo.repository.HotspotZoneRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zones")
public class HotspotZoneController {

    private final HotspotZoneRepository zoneRepo;

    public HotspotZoneController(HotspotZoneRepository zoneRepo) {
        this.zoneRepo = zoneRepo;
    }

    @PostMapping
    public HotspotZoneEntity createZone(@RequestBody HotspotZoneEntity zone) {
        return zoneRepo.save(zone);
    }

    @GetMapping
    public List<HotspotZoneEntity> getAllZones() {
        return zoneRepo.findAll();
    }

    @GetMapping("/{id}")
    public HotspotZoneEntity getZoneById(@PathVariable Long id) {
        return zoneRepo.findById(id).orElse(null);
    }
}

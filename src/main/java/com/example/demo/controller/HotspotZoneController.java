package com.example.demo.controller;

import com.example.demo.model.HotspotZone;
import com.example.demo.service.HotspotZoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zones")
public class HotspotZoneController {

    private final HotspotZoneService hotspotZoneService;

    public HotspotZoneController(HotspotZoneService hotspotZoneService) {
        this.hotspotZoneService = hotspotZoneService;
    }

    @PostMapping
    public HotspotZone addZone(@RequestBody HotspotZone zone) {
        return hotspotZoneService.addZone(zone);
    }

    @GetMapping
    public List<HotspotZone> getAllZones() {
        return hotspotZoneService.getAllZones();
    }
}

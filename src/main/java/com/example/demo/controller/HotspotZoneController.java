/*package com.example.demo.controller;

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
*/
// src/main/java/com/example/demo/controller/HotspotZoneController.java

package com.example.demo.controller;

import com.example.demo.model.HotspotZone;
import com.example.demo.service.HotspotZoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/zones")
@Tag(name = "Hotspot Zones", description = "Hotspot zone management")
@SecurityRequirement(name = "bearerAuth")
public class HotspotZoneController {
    
    private final HotspotZoneService hotspotZoneService;
    
    public HotspotZoneController(HotspotZoneService hotspotZoneService) {
        this.hotspotZoneService = hotspotZoneService;
    }
    
    @PostMapping
    @Operation(summary = "Create a new hotspot zone")
    public ResponseEntity<?> addZone(@RequestBody HotspotZone zone) {
        try {
            HotspotZone savedZone = hotspotZoneService.addZone(zone);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedZone);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    @Operation(summary = "Retrieve all hotspot zones")
    public ResponseEntity<List<HotspotZone>> getAllZones() {
        List<HotspotZone> zones = hotspotZoneService.getAllZones();
        return ResponseEntity.ok(zones);
    }
}
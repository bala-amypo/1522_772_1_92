package com.example.demo.controller;

import com.example.demo.entity.HotspotZoneEntity;
import com.example.demo.repository.HotspotZoneRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zones")
public class HotspotZoneController {

    private final HotspotZoneRepository repo;

    public HotspotZoneController(HotspotZoneRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public HotspotZoneEntity create(@Valid @RequestBody HotspotZoneEntity zone) {
        return repo.save(zone);
    }

    @GetMapping
    public List<HotspotZoneEntity> getAll() {
        return repo.findAll();
    }
}

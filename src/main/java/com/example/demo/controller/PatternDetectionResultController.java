package com.example.demo.controller;

import com.example.demo.entity.PatternDetectionResultEntity;
import com.example.demo.service.PatternDetectionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pattern")
public class PatternDetectionController {

    private final PatternDetectionService service;

    public PatternDetectionController(PatternDetectionService service) {
        this.service = service;
    }

    @GetMapping("/{zoneId}")
    public PatternDetectionResultEntity detectPattern(@PathVariable Long zoneId) {
        return service.detect(zoneId);
    }
}

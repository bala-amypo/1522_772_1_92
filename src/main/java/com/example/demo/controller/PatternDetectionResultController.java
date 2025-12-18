package com.example.demo.controller;

import com.example.demo.entity.PatternDetectionResultEntity;
import com.example.demo.service.PatternDetectionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pattern")
public class PatternDetectionResultController {

    private final PatternDetectionResultService service;

    public PatternDetectionController(PatternDetectionResultService service) {
        this.service = service;
    }

    @GetMapping("/{zoneId}")
    public PatternDetectionResultEntity detectPattern(@PathVariable Long zoneId) {
        return service.detect(zoneId);
    }
}

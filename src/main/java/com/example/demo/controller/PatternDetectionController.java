package com.example.demo.controller;

import com.example.demo.model.PatternDetectionResult;
import com.example.demo.service.PatternDetectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patterns")
public class PatternDetectionController {

    private final PatternDetectionService patternService;

    public PatternDetectionController(PatternDetectionService patternService) {
        this.patternService = patternService;
    }

    @PostMapping("/detect/{zoneId}")
    public PatternDetectionResult detect(@PathVariable Long zoneId) {
        return patternService.detectPattern(zoneId);
    }

    @GetMapping("/zone/{zoneId}")
    public List<PatternDetectionResult> getResults(@PathVariable Long zoneId) {
        return patternService.getResultsByZone(zoneId);
    }
}

/*package com.example.demo.controller;

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
*/
package com.example.demo.controller;

import com.example.demo.model.PatternDetectionResult;
import com.example.demo.service.PatternDetectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patterns")
@Tag(name = "Pattern Detection", description = "Crime pattern detection")
@SecurityRequirement(name = "bearerAuth")
public class PatternDetectionController {
    
    private final PatternDetectionService patternDetectionService;
    
    public PatternDetectionController(PatternDetectionService patternDetectionService) {
        this.patternDetectionService = patternDetectionService;
    }
    
    @PostMapping("/detect/{zoneId}")
    @Operation(summary = "Execute pattern detection for specified zone")
    public ResponseEntity<?> detectPattern(@PathVariable Long zoneId) {
        try {
            PatternDetectionResult result = 
                patternDetectionService.detectPattern(zoneId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/zone/{zoneId}")
    @Operation(summary = "Retrieve all detection results for a zone")
    public ResponseEntity<List<PatternDetectionResult>> getResultsByZone(
            @PathVariable Long zoneId) {
        List<PatternDetectionResult> results = 
            patternDetectionService.getResultsByZone(zoneId);
        return ResponseEntity.ok(results);
    }
}
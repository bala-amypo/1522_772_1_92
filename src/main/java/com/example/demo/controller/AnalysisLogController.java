/*package com.example.demo.controller;

import com.example.demo.model.AnalysisLog;
import com.example.demo.service.AnalysisLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logs")
public class AnalysisLogController {

    private final AnalysisLogService logService;

    public AnalysisLogController(AnalysisLogService logService) {
        this.logService = logService;
    }

    @PostMapping("/{zoneId}")
    public AnalysisLog addLog(@PathVariable Long zoneId,
                              @RequestBody Map<String, String> body) {
        return logService.addLog(zoneId, body.get("message"));
    }

    @GetMapping("/zone/{zoneId}")
    public List<AnalysisLog> getLogs(@PathVariable Long zoneId) {
        return logService.getLogsByZone(zoneId);
    }
}
*/
package com.example.demo.controller;

import com.example.demo.model.AnalysisLog;
import com.example.demo.service.AnalysisLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logs")
@Tag(name = "Analysis Logs", description = "Analysis log management")
@SecurityRequirement(name = "bearerAuth")
public class AnalysisLogController {
    
    private final AnalysisLogService analysisLogService;
    
    public AnalysisLogController(AnalysisLogService analysisLogService) {
        this.analysisLogService = analysisLogService;
    }
    
    @PostMapping("/{zoneId}")
    @Operation(summary = "Create analysis log entry")
    public ResponseEntity<?> addLog(@PathVariable Long zoneId, 
                                    @RequestBody Map<String, String> request) {
        try {
            String message = request.get("message");
            AnalysisLog log = analysisLogService.addLog(zoneId, message);
            return ResponseEntity.status(HttpStatus.CREATED).body(log);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/zone/{zoneId}")
    @Operation(summary = "Retrieve all logs for a zone")
    public ResponseEntity<List<AnalysisLog>> getLogsByZone(@PathVariable Long zoneId) {
        List<AnalysisLog> logs = analysisLogService.getLogsByZone(zoneId);
        return ResponseEntity.ok(logs);
    }
}
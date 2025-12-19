package com.example.demo.controller;

import com.example.demo.model.AnalysisLog;
import com.example.demo.service.AnalysisLogService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class AnalysisLogController {

    private final AnalysisLogService logService;

    public AnalysisLogController(AnalysisLogService logService) {
        this.logService = logService;
    }

    @PostMapping("/{zoneId}")
    public AnalysisLog addLog(@PathVariable Long zoneId, @RequestBody String message) {
        return logService.addLog(zoneId, message);
    }

    @GetMapping("/zone/{zoneId}")
    public List<AnalysisLog> getLogsByZone(@PathVariable Long zoneId) {
        return logService.getLogsByZone(zoneId);
    }
}

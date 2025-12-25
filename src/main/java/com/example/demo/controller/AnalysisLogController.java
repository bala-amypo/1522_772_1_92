package com.example.demo.controller;

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

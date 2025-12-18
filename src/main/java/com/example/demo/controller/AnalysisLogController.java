package com.example.demo.controller;

import com.example.demo.entity.AnalysisLogEntity;
import com.example.demo.repository.AnalysisLogRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class AnalysisLogController {

    private final AnalysisLogRepository logRepo;

    public AnalysisLogController(AnalysisLogRepository logRepo) {
        this.logRepo = logRepo;
    }

    @GetMapping
    public List<AnalysisLogEntity> getAllLogs() {
        return logRepo.findAll();
    }

    @GetMapping("/{id}")
    public AnalysisLogEntity getLogById(@PathVariable Long id) {
        return logRepo.findById(id).orElse(null);
    }
}

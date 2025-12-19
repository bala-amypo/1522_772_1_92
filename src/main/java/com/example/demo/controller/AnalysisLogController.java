package com.example.demo.controller;

import com.example.demo.entity.AnalysisLogEntity;
import com.example.demo.repository.AnalysisLogRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analysis-logs")
public class AnalysisLogController {

    private final AnalysisLogRepository repo;

    public AnalysisLogController(AnalysisLogRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<AnalysisLogEntity> getAll() {
        return repo.findAll();
    }
}

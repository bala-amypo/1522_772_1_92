package com.example.demo.controller;

import com.example.demo.entity.CrimeReportEntity;
import com.example.demo.repository.CrimeReportRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crime-reports")
public class CrimeReportController {

    private final CrimeReportRepository repo;

    public CrimeReportController(CrimeReportRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public CrimeReportEntity create(@Valid @RequestBody CrimeReportEntity report) {
        return repo.save(report);
    }

    @GetMapping
    public List<CrimeReportEntity> getAll() {
        return repo.findAll();
    }
}

package com.example.demo.controller;

import com.example.demo.entity.CrimeReportEntity;
import com.example.demo.repository.CrimeReportRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crimes")
public class CrimeReportController {

    private final CrimeReportRepository crimeRepo;

    public CrimeReportController(CrimeReportRepository crimeRepo) {
        this.crimeRepo = crimeRepo;
    }

    @PostMapping
    public CrimeReportEntity addCrime(@RequestBody CrimeReportEntity crime) {
        return crimeRepo.save(crime);
    }

    @GetMapping
    public List<CrimeReportEntity> getAllCrimes() {
        return crimeRepo.findAll();
    }

    @GetMapping("/{id}")
    public CrimeReportEntity getCrimeById(@PathVariable Long id) {
        return crimeRepo.findById(id).orElse(null);
    }
}

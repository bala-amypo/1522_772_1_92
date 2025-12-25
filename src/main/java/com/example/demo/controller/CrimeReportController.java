package com.example.demo.controller;

import com.example.demo.model.CrimeReport;
import com.example.demo.service.CrimeReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class CrimeReportController {

    private final CrimeReportService reportService;

    public CrimeReportController(CrimeReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public CrimeReport addReport(@RequestBody CrimeReport report) {
        return reportService.addReport(report);
    }

    @GetMapping
    public List<CrimeReport> getAllReports() {
        return reportService.getAllReports();
    }
}

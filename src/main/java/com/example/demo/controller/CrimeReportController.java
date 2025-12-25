/*package com.example.demo.controller;

import com.example.demo.model.CrimeReport;
import com.example.demo.service.CrimeReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class CrimeReportController {

    private final CrimeReportService crimeReportService;

    public CrimeReportController(CrimeReportService crimeReportService) {
        this.crimeReportService = crimeReportService;
    }

    @PostMapping
    public CrimeReport addReport(@RequestBody CrimeReport report) {
        return crimeReportService.addReport(report);
    }

    @GetMapping
    public List<CrimeReport> getAllReports() {
        return crimeReportService.getAllReports();
    }
}
*/
package com.example.demo.controller;

import com.example.demo.model.CrimeReport;
import com.example.demo.service.CrimeReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reports")
@Tag(name = "Crime Reports", description = "Crime report management")
@SecurityRequirement(name = "bearerAuth")
public class CrimeReportController {
    
    private final CrimeReportService crimeReportService;
    
    public CrimeReportController(CrimeReportService crimeReportService) {
        this.crimeReportService = crimeReportService;
    }
    
    @PostMapping
    @Operation(summary = "Submit a new crime report")
    public ResponseEntity<?> addReport(@RequestBody CrimeReport report) {
        try {
            CrimeReport savedReport = crimeReportService.addReport(report);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReport);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    @Operation(summary = "Retrieve all crime reports")
    public ResponseEntity<List<CrimeReport>> getAllReports() {
        List<CrimeReport> reports = crimeReportService.getAllReports();
        return ResponseEntity.ok(reports);
    }
}


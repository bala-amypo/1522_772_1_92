/*package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.PatternDetectionService;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PatternDetectionServiceImpl implements PatternDetectionService {

    private final HotspotZoneRepository zoneRepo;
    private final CrimeReportRepository reportRepo;
    private final PatternDetectionResultRepository resultRepo;
    private final AnalysisLogRepository logRepo;

    public PatternDetectionServiceImpl(
            HotspotZoneRepository zoneRepo,
            CrimeReportRepository reportRepo,
            PatternDetectionResultRepository resultRepo,
            AnalysisLogRepository logRepo) {

        this.zoneRepo = zoneRepo;
        this.reportRepo = reportRepo;
        this.resultRepo = resultRepo;
        this.logRepo = logRepo;
    }

    @Override
    public PatternDetectionResult detectPattern(Long zoneId) {

        HotspotZone zone = zoneRepo.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("zone not found"));

        double minLat = zone.getCenterLat() - 0.1;
        double maxLat = zone.getCenterLat() + 0.1;
        double minLong = zone.getCenterLong() - 0.1;
        double maxLong = zone.getCenterLong() + 0.1;

        List<CrimeReport> crimes =
                reportRepo.findByLatLongRange(minLat, maxLat, minLong, maxLong);

        int count = crimes.size();

        String pattern;
        String severity;

        if (count > 5) {
            pattern = "High Risk Pattern Detected";
            severity = "HIGH";
        } else if (count > 0) {
            pattern = "Medium Risk Pattern Detected";
            severity = "MEDIUM";
        } else {
            pattern = "No Pattern Detected";
            severity = "LOW";
        }

        PatternDetectionResult result = new PatternDetectionResult();
        result.setZone(zone);
        result.setCrimeCount(count);
        result.setDetectedPattern(pattern);
        result.setAnalysisDate(LocalDate.now());

        resultRepo.save(result);

        zone.setSeverityLevel(severity);
        zoneRepo.save(zone);

        AnalysisLog log = new AnalysisLog(
                "Pattern detection completed for zone",
                zone
        );
        logRepo.save(log);

        return result;
    }

    @Override
    public List<PatternDetectionResult> getResultsByZone(Long zoneId) {
        return resultRepo.findByZone_Id(zoneId);
    }
}
*/
package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AnalysisLog;
import com.example.demo.model.HotspotZone;
import com.example.demo.model.PatternDetectionResult;
import com.example.demo.model.CrimeReport;
import com.example.demo.repository.AnalysisLogRepository;
import com.example.demo.repository.CrimeReportRepository;
import com.example.demo.repository.HotspotZoneRepository;
import com.example.demo.repository.PatternDetectionResultRepository;
import com.example.demo.service.PatternDetectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class PatternDetectionServiceImpl implements PatternDetectionService {
    
    private final HotspotZoneRepository zoneRepo;
    private final CrimeReportRepository reportRepo;
    private final PatternDetectionResultRepository resultRepo;
    private final AnalysisLogRepository logRepo;
    
    public PatternDetectionServiceImpl(HotspotZoneRepository zoneRepo,
                                       CrimeReportRepository reportRepo,
                                       PatternDetectionResultRepository resultRepo,
                                       AnalysisLogRepository logRepo) {
        this.zoneRepo = zoneRepo;
        this.reportRepo = reportRepo;
        this.resultRepo = resultRepo;
        this.logRepo = logRepo;
    }
    
    @Override
    @Transactional
    public PatternDetectionResult detectPattern(Long zoneId) throws Exception {
        // Fetch zone
        HotspotZone zone = zoneRepo.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Zone not found with id: " + zoneId));
        
        // Calculate search bounds (center ± 0.1 degrees)
        double minLat = zone.getCenterLat() - 0.1;
        double maxLat = zone.getCenterLat() + 0.1;
        double minLong = zone.getCenterLong() - 0.1;
        double maxLong = zone.getCenterLong() + 0.1;
        
        // Query crimes within bounds
        List<CrimeReport> crimesInZone = reportRepo.findByLatLongRange(
            minLat, maxLat, minLong, maxLong);
        
        // Count crimes
        int crimeCount = crimesInZone.size();
        
        // Determine pattern based on thresholds
        String detectedPattern;
        String newSeverity;
        
        if (crimeCount > 5) {
            detectedPattern = "High Risk Pattern Detected";
            newSeverity = "HIGH";
        } else if (crimeCount > 0) {
            detectedPattern = "Medium Risk Pattern Detected";
            newSeverity = "MEDIUM";
        } else {
            detectedPattern = "No Pattern Detected";
            newSeverity = "LOW";
        }
        
        // Create and save pattern detection result
        PatternDetectionResult result = new PatternDetectionResult();
        result.setZone(zone);
        result.setAnalysisDate(LocalDate.now());
        result.setCrimeCount(crimeCount);
        result.setDetectedPattern(detectedPattern);
        result = resultRepo.save(result);
        
        // Update zone severity
        zone.setSeverityLevel(newSeverity);
        zoneRepo.save(zone);
        
        // Create analysis log
        AnalysisLog log = new AnalysisLog();
        log.setZone(zone);
        log.setMessage("Pattern detection completed for zone " + zone.getZoneName() + 
                      ". Found " + crimeCount + " crimes. Pattern: " + detectedPattern);
        logRepo.save(log);
        
        return result;
    }
    
    @Override
    public List<PatternDetectionResult> getResultsByZone(Long zoneId) {
        return resultRepo.findByZone_Id(zoneId);
    }
}
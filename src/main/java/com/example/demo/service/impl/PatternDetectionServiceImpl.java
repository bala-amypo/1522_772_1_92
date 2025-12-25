package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.PatternDetectionService;

import java.time.LocalDate;
import java.util.List;
import com.example.demo.exception.ResourceNotFoundException;
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
        if (count > 5) {
            pattern = "High Risk Pattern Detected";
            zone.setSeverityLevel("HIGH");
        } else if (count > 0) {
            pattern = "Medium Risk Pattern Detected";
            zone.setSeverityLevel("MEDIUM");
        } else {
            pattern = "No Pattern Detected";
            zone.setSeverityLevel("LOW");
        }

        PatternDetectionResult result = new PatternDetectionResult(
                zone,
                LocalDate.now(),
                count,
                pattern
        );

        resultRepo.save(result);

        AnalysisLog log = new AnalysisLog(
                "Pattern detection completed",
                zone
        );

        logRepo.save(log);
        zoneRepo.save(zone);

        return result;
    }

    @Override
    public List<PatternDetectionResult> getResultsByZone(Long zoneId) {
        return resultRepo.findByZone_Id(zoneId);
    }
}

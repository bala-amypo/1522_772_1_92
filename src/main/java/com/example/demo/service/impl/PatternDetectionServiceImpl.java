package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.PatternDetectionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PatternDetectionServiceImpl implements PatternDetectionService {

    private final HotspotZoneRepository zoneRepo;
    private final CrimeReportRepository crimeRepo;
    private final PatternDetectionResultRepository resultRepo;
    private final AnalysisLogRepository logRepo;

    public PatternDetectionServiceImpl(
            HotspotZoneRepository zoneRepo,
            CrimeReportRepository crimeRepo,
            PatternDetectionResultRepository resultRepo,
            AnalysisLogRepository logRepo) {
        this.zoneRepo = zoneRepo;
        this.crimeRepo = crimeRepo;
        this.resultRepo = resultRepo;
        this.logRepo = logRepo;
    }

    @Override
    public PatternDetectionResultEntity detect(Long zoneId) {

        HotspotZoneEntity zone = zoneRepo.findById(zoneId)
                .orElseThrow(() -> new RuntimeException("Zone not found"));

        int count = crimeRepo
                .findByLatitudeBetweenAndLongitudeBetween(
                        zone.getCenterLat() - 0.1,
                        zone.getCenterLat() + 0.1,
                        zone.getCenterLong() - 0.1,
                        zone.getCenterLong() + 0.1)
                .size();

        String pattern = count > 5 ? "High" :
                         count > 0 ? "Medium" : "No";

        PatternDetectionResultEntity result = new PatternDetectionResultEntity();
        result.setZone(zone);
        result.setCrimeCount(count);
        result.setDetectedPattern(pattern);
        result.setAnalysisDate(LocalDate.now());
        resultRepo.save(result);

        AnalysisLogEntity log = new AnalysisLogEntity();
        log.setZone(zone);
        log.setMessage("Pattern detected: " + pattern);
        logRepo.save(log);

        return result;
    }
}

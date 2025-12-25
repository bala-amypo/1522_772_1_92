/*package com.example.demo.service.impl;

import com.example.demo.model.CrimeReport;
import com.example.demo.repository.CrimeReportRepository;
import com.example.demo.service.CrimeReportService;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CrimeReportServiceImpl implements CrimeReportService {

    private final CrimeReportRepository reportRepository;

    public CrimeReportServiceImpl(CrimeReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public CrimeReport addReport(CrimeReport report) {

        if (report.getLatitude() < -90 || report.getLatitude() > 90) {
            throw new IllegalArgumentException("latitude out of range");
        }

        if (report.getLongitude() < -180 || report.getLongitude() > 180) {
            throw new IllegalArgumentException("longitude out of range");
        }

        if (report.getOccurredAt().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("crime time cannot be in future");
        }

        return reportRepository.save(report);
    }

    @Override
    public List<CrimeReport> getAllReports() {
        return reportRepository.findAll();
    }
}
*/
package com.example.demo.service.impl;

import com.example.demo.model.CrimeReport;
import com.example.demo.repository.CrimeReportRepository;
import com.example.demo.service.CrimeReportService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CrimeReportServiceImpl implements CrimeReportService {
    
    private final CrimeReportRepository reportRepository;
    
    public CrimeReportServiceImpl(CrimeReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
    
    @Override
    public CrimeReport addReport(CrimeReport report) throws Exception {
        // Validate coordinates
        if (report.getLatitude() == null || report.getLatitude() < -90 || 
            report.getLatitude() > 90) {
            throw new IllegalArgumentException(
                "Validation failed: Latitude must be between -90 and 90");
        }
        
        if (report.getLongitude() == null || report.getLongitude() < -180 || 
            report.getLongitude() > 180) {
            throw new IllegalArgumentException(
                "Validation failed: Longitude must be between -180 and 180");
        }
        
        // Validate occurredAt is not in future
        if (report.getOccurredAt() != null && 
            report.getOccurredAt().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                "Validation failed: Crime occurrence time cannot be in the future");
        }
        
        return reportRepository.save(report);
    }
    
    @Override
    public List<CrimeReport> getAllReports() {
        return reportRepository.findAll();
    }
}
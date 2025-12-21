package com.example.demo.service.impl;

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

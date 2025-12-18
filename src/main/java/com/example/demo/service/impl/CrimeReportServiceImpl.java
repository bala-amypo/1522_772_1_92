package com.example.demo.service.impl;

import com.example.demo.entity.CrimeReportEntity;
import com.example.demo.repository.CrimeReportRepository;
import com.example.demo.service.CrimeReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrimeReportServiceImpl implements CrimeReportService {

    private final CrimeReportRepository crimeRepo;

    public CrimeReportServiceImpl(CrimeReportRepository crimeRepo) {
        this.crimeRepo = crimeRepo;
    }

    @Override
    public CrimeReportEntity save(CrimeReportEntity crime) {
        return crimeRepo.save(crime);
    }

    @Override
    public List<CrimeReportEntity> getAll() {
        return crimeRepo.findAll();
    }

    @Override
    public CrimeReportEntity getById(Long id) {
        return crimeRepo.findById(id).orElse(null);
    }
}

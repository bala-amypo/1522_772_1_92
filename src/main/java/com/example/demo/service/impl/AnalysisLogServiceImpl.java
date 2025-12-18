package com.example.demo.service.impl;

import com.example.demo.entity.AnalysisLogEntity;
import com.example.demo.repository.AnalysisLogRepository;
import com.example.demo.service.AnalysisLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisLogServiceImpl implements AnalysisLogService {

    private final AnalysisLogRepository logRepo;

    public AnalysisLogServiceImpl(AnalysisLogRepository logRepo) {
        this.logRepo = logRepo;
    }

    @Override
    public List<AnalysisLogEntity> getAll() {
        return logRepo.findAll();
    }

    @Override
    public AnalysisLogEntity getById(Long id) {
        return logRepo.findById(id).orElse(null);
    }
}

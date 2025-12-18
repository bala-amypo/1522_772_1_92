package com.example.demo.service;

import com.example.demo.entity.AnalysisLogEntity;
import java.util.List;

public interface AnalysisLogService {

    List<AnalysisLogEntity> getAll();

    AnalysisLogEntity getById(Long id);
}

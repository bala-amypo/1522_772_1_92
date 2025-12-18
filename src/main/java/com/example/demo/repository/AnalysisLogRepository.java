package com.example.demo.repository;

import com.example.demo.entity.AnalysisLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisLogRepository
        extends JpaRepository<AnalysisLogEntity, Long> {
}

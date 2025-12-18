package com.example.demo.repository;

import com.example.demo.entity.PatternDetectionResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatternDetectionResultRepository
        extends JpaRepository<PatternDetectionResultEntity, Long> {
}

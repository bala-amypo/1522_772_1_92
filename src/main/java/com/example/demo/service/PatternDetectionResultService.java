package com.example.demo.service;

import com.example.demo.entity.PatternDetectionResultEntity;

public interface PatternDetectionService {

    PatternDetectionResultEntity detect(Long zoneId);
}

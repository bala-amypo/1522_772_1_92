package com.example.demo.service;

import com.example.demo.entity.CrimeReportEntity;
import java.util.List;

public interface CrimeReportService {

    CrimeReportEntity save(CrimeReportEntity crime);

    List<CrimeReportEntity> getAll();

    CrimeReportEntity getById(Long id);
}

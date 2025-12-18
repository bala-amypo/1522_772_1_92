package com.example.demo.repository;

import com.example.demo.entity.CrimeReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrimeReportRepository
        extends JpaRepository<CrimeReportEntity, Long> {

    List<CrimeReportEntity> findByLatitudeBetweenAndLongitudeBetween(
            double minLat, double maxLat,
            double minLong, double maxLong
    );
}

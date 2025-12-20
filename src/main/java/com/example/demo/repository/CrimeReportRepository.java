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
package com.example.demo.repository;

import com.example.demo.model.CrimeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CrimeReportRepository extends JpaRepository<CrimeReport, Long> {

    @Query("SELECT c FROM CrimeReport c WHERE c.latitude BETWEEN :minLat AND :maxLat AND c.longitude BETWEEN :minLong AND :maxLong")
    List<CrimeReport> findByLatLongRange(double minLat, double maxLat, double minLong, double maxLong);
}

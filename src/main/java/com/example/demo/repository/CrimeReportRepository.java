package com.example.demo.repository;

import com.example.demo.model.CrimeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CrimeReportRepository extends JpaRepository<CrimeReport, Long> {

    @Query("SELECT c FROM CrimeReport c WHERE c.latitude BETWEEN ?1 AND ?2 AND c.longitude BETWEEN ?3 AND ?4")
    List<CrimeReport> findByLatLongRange(double minLat,
                                         double maxLat,
                                         double minLong,
                                         double maxLong);
}

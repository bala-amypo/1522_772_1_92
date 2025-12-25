/*package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AnalysisLog;
import com.example.demo.model.HotspotZone;
import com.example.demo.repository.AnalysisLogRepository;
import com.example.demo.repository.HotspotZoneRepository;
import com.example.demo.service.AnalysisLogService;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AnalysisLogServiceImpl implements AnalysisLogService {

    private final AnalysisLogRepository logRepo;
    private final HotspotZoneRepository zoneRepo;

    public AnalysisLogServiceImpl(AnalysisLogRepository logRepo,
                                  HotspotZoneRepository zoneRepo) {
        this.logRepo = logRepo;
        this.zoneRepo = zoneRepo;
    }

    @Override
    public AnalysisLog addLog(Long zoneId, String message) {

        HotspotZone zone = zoneRepo.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("zone not found"));

        AnalysisLog log = new AnalysisLog(message, zone);
        return logRepo.save(log);
    }

    @Override
    public List<AnalysisLog> getLogsByZone(Long zoneId) {
        return logRepo.findByZone_Id(zoneId);
    }
}
*/
package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AnalysisLog;
import com.example.demo.model.HotspotZone;
import com.example.demo.repository.AnalysisLogRepository;
import com.example.demo.repository.HotspotZoneRepository;
import com.example.demo.service.AnalysisLogService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnalysisLogServiceImpl implements AnalysisLogService {
    
    private final AnalysisLogRepository logRepo;
    private final HotspotZoneRepository zoneRepo;
    
    public AnalysisLogServiceImpl(AnalysisLogRepository logRepo,
                                  HotspotZoneRepository zoneRepo) {
        this.logRepo = logRepo;
        this.zoneRepo = zoneRepo;
    }
    
    @Override
    public AnalysisLog addLog(Long zoneId, String message) throws Exception {
        HotspotZone zone = zoneRepo.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Zone not found with id: " + zoneId));
        
        AnalysisLog log = new AnalysisLog();
        log.setZone(zone);
        log.setMessage(message);
        
        return logRepo.save(log);
    }
    
    @Override
    public List<AnalysisLog> getLogsByZone(Long zoneId) {
        return logRepo.findByZone_Id(zoneId);
    }
}
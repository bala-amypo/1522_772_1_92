package com.example.demo.service;

import com.example.demo.entity.HotspotZoneEntity;
import java.util.List;

public interface HotspotZoneService {

    HotspotZoneEntity save(HotspotZoneEntity zone);

    List<HotspotZoneEntity> getAll();

    HotspotZoneEntity getById(Long id);
}

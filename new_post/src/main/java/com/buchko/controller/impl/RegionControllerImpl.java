package com.buchko.controller.impl;

import com.buchko.controller.RegionController;
import com.buchko.domain.Region;
import com.buchko.service.RegionService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionControllerImpl implements RegionController {

    @Autowired
    RegionService regionService;

    @Override
    public List<Region> findAll() {
        return regionService.findAll();
    }

    @Override
    public Optional<Region> findById(String id) {
        return regionService.findById(id);
    }

    @Override
    public int create(Region entity) {
        return regionService.create(entity);
    }

    @Override
    public int update(String id, Region entity) {
        return regionService.update(id, entity);
    }

    @Override
    public int delete(String id) {
        return regionService.delete(id);
    }
}

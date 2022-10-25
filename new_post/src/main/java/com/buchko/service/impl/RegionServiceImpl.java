package com.buchko.service.impl;

import com.buchko.dao.RegionDao;
import com.buchko.domain.Region;
import com.buchko.service.RegionService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    RegionDao regionDao;

    @Override
    public List<Region> findAll() {
        return regionDao.findAll();
    }

    @Override
    public Optional<Region> findById(String id) {
        return regionDao.findById(id);
    }

    @Override
    public int create(Region entity) {
        return regionDao.create(entity);
    }

    @Override
    public int update(String id, Region entity) {
        return regionDao.update(id, entity);
    }

    @Override
    public int delete(String id) {
        return regionDao.delete(id);
    }
}

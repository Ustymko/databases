package com.buchko.service.impl;

import com.buchko.dao.CityDao;
import com.buchko.domain.City;
import com.buchko.service.CityService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityDao cityDao;

    @Override
    public List<City> findCitiesByRegionName(String regionName) {
        return cityDao.findCitiesByRegionName(regionName);
    }

    @Override
    public List<City> findAll() {
        return cityDao.findAll();
    }

    @Override
    public Optional<City> findById(Integer id) {
        return cityDao.findById(id);
    }

    @Override
    public int create(City entity) {
        return cityDao.create(entity);
    }

    @Override
    public int update(Integer id, City entity) {
        return cityDao.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return cityDao.delete(id);
    }
}

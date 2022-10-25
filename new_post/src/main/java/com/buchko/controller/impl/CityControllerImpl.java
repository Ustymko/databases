package com.buchko.controller.impl;

import com.buchko.controller.CityController;
import com.buchko.domain.City;
import com.buchko.service.CityService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityControllerImpl implements CityController {

    @Autowired
    CityService cityService;

    @Override
    public List<City> findCitiesByRegionName(String regionName) {
        return cityService.findCitiesByRegionName(regionName);
    }

    @Override
    public List<City> findAll() {
        return cityService.findAll();
    }

    @Override
    public Optional<City> findById(Integer id) {
        return cityService.findById(id);
    }

    @Override
    public int create(City entity) {
        return cityService.create(entity);
    }

    @Override
    public int update(Integer id, City entity) {
        return cityService.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return cityService.delete(id);
    }
}

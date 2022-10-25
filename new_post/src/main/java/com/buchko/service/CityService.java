package com.buchko.service;

import com.buchko.domain.City;
import java.util.List;

public interface CityService extends GeneralService<City, Integer>{
    List<City> findCitiesByRegionName(String regionName);
}

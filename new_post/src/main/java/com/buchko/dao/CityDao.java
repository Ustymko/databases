package com.buchko.dao;

import com.buchko.domain.City;
import java.util.List;

public interface CityDao extends GeneralDao<City, Integer>{
    List<City> findCitiesByRegionName(String regionName);
}

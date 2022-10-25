package com.buchko.controller;

import com.buchko.controller.GeneralController;
import com.buchko.domain.City;
import java.util.List;

public interface CityController extends GeneralController<City, Integer> {
    List<City> findCitiesByRegionName(String regionName);
}

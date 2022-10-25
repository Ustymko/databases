package com.buchko.controller;

import com.buchko.domain.Department;
import java.util.List;

public interface DepartmentController extends GeneralController<Department, Integer> {
    List<Department> findByNumber(Integer number);

    List<Department> findByCityId(Integer cityId);
}

package com.buchko.service;

import com.buchko.domain.Department;
import java.util.List;

public interface DepartmentService extends GeneralService<Department, Integer>{
    List<Department> findByNumber(Integer number);

    List<Department> findByCityId(Integer cityId);
}

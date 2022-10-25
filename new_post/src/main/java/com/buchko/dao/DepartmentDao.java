package com.buchko.dao;

import com.buchko.domain.Department;
import java.util.List;

public interface DepartmentDao extends GeneralDao<Department, Integer>{
    List<Department> findByNumber(Integer number);

    List<Department> findByCityId(Integer cityId);
}

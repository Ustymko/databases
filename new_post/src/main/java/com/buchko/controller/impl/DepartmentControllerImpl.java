package com.buchko.controller.impl;

import com.buchko.controller.DepartmentController;
import com.buchko.domain.Department;
import com.buchko.service.DepartmentService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentControllerImpl implements DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @Override
    public List<Department> findByNumber(Integer number) {
        return departmentService.findByNumber(number);
    }

    @Override
    public List<Department> findByCityId(Integer cityId) {
        return departmentService.findByCityId(cityId);
    }

    @Override
    public List<Department> findAll() {
        return departmentService.findAll();
    }

    @Override
    public Optional<Department> findById(Integer id) {
        return departmentService.findById(id);
    }

    @Override
    public int create(Department entity) {
        return departmentService.create(entity);
    }

    @Override
    public int update(Integer id, Department entity) {
        return departmentService.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return departmentService.delete(id);
    }
}

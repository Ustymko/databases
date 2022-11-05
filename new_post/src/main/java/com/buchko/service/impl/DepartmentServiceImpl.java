package com.buchko.service.impl;

import com.buchko.dao.DepartmentDao;
import com.buchko.domain.Department;
import com.buchko.service.DepartmentService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentDao departmentDao;

    @Override
    public List<Department> findByNumber(Integer number) {
        return departmentDao.findByNumber(number);
    }

    @Override
    public List<Department> findByCityId(Integer cityId) {
        return departmentDao.findByCityId(cityId);
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }

    @Override
    public Optional<Department> findById(Integer id) {
        return departmentDao.findById(id);
    }

    @Override
    public int create(Department entity) {
        return departmentDao.create(entity);
    }

    @Override
    public int update(Integer id, Department entity) {
        return departmentDao.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return departmentDao.delete(id);
    }
}

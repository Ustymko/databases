package com.buchko.service.impl;

import com.buchko.dao.CourierDao;
import com.buchko.domain.Courier;
import com.buchko.service.CourierService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourierServiceImpl implements CourierService {

    @Autowired
    CourierDao courierDao;

    @Override
    public List<Courier> findAll() {
        return courierDao.findAll();
    }

    @Override
    public Optional<Courier> findById(Integer id) {
        return courierDao.findById(id);
    }

    @Override
    public int create(Courier entity) {
        return courierDao.create(entity);
    }

    @Override
    public int update(Integer id, Courier entity) {
        return courierDao.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return courierDao.delete(id);
    }

    @Override
    public List<Courier> findByDepartmentId(Integer departmentId) {
        return courierDao.findByDepartmentId(departmentId);
    }
}

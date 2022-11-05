package com.buchko.controller.impl;

import com.buchko.controller.CourierController;
import com.buchko.domain.Courier;
import com.buchko.service.CourierService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourierControllerImpl implements CourierController {

    @Autowired
    CourierService courierService;

    @Override
    public List<Courier> findAll() {
        return courierService.findAll();
    }

    @Override
    public Optional<Courier> findById(Integer id) {
        return courierService.findById(id);
    }

    @Override
    public int create(Courier entity) {
        return courierService.create(entity);
    }

    @Override
    public int update(Integer id, Courier entity) {
        return courierService.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return courierService.delete(id);
    }

    @Override
    public List<Courier> findByDepartmentId(Integer departmentId) {
        return courierService.findByDepartmentId(departmentId);
    }
}

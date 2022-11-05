package com.buchko.controller.impl;

import com.buchko.controller.OperatorController;
import com.buchko.domain.Operator;
import com.buchko.service.OperatorService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorControllerImpl implements OperatorController {

    @Autowired
    OperatorService operatorService;

    @Override
    public List<Operator> findAll() {
        return operatorService.findAll();
    }

    @Override
    public Optional<Operator> findById(Integer id) {
        return operatorService.findById(id);
    }

    @Override
    public int create(Operator entity) {
        return operatorService.create(entity);
    }

    @Override
    public int update(Integer id, Operator entity) {
        return operatorService.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return operatorService.delete(id);
    }

    @Override
    public List<Operator> findByDepartmentId(Integer departmentId) {
        return operatorService.findByDepartmentId(departmentId);
    }
}

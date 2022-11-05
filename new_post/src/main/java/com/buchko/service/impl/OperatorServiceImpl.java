package com.buchko.service.impl;

import com.buchko.dao.OperatorDao;
import com.buchko.domain.Operator;
import com.buchko.service.OperatorService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    OperatorDao operatorDao;

    @Override
    public List<Operator> findAll() {
        return operatorDao.findAll();
    }

    @Override
    public Optional<Operator> findById(Integer id) {
        return operatorDao.findById(id);
    }

    @Override
    public int create(Operator entity) {
        return operatorDao.create(entity);
    }

    @Override
    public int update(Integer id, Operator entity) {
        return operatorDao.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return operatorDao.delete(id);
    }

    @Override
    public List<Operator> findByDepartmentId(Integer departmentId) {
        return operatorDao.findByDepartmentId(departmentId);
    }
}

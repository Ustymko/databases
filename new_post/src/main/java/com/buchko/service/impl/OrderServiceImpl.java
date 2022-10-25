package com.buchko.service.impl;

import com.buchko.dao.OrderDao;
import com.buchko.domain.Order;
import com.buchko.service.OrderService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderDao.findById(id);
    }

    @Override
    public int create(Order entity) {
        return orderDao.create(entity);
    }

    @Override
    public int update(Integer id, Order entity) {
        return orderDao.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return orderDao.delete(id);
    }

    @Override
    public List<Order> findBySenderClientId(Integer senderClientId) {
        return orderDao.findBySenderClientId(senderClientId);
    }

    @Override
    public List<Order> findByReceiverClientId(Integer receiverClientId) {
        return orderDao.findByReceiverClientId(receiverClientId);
    }

    @Override
    public List<Order> findByReceiverDepartmentId(Integer receiverDepartmentId) {
        return orderDao.findByReceiverDepartmentId(receiverDepartmentId);
    }
}

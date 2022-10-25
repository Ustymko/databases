package com.buchko.controller.impl;

import com.buchko.controller.OrderController;
import com.buchko.domain.Order;
import com.buchko.service.OrderService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderControllerImpl implements OrderController {

    @Autowired
    OrderService orderService;

    @Override
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderService.findById(id);
    }

    @Override
    public int create(Order entity) {
        return orderService.create(entity);
    }

    @Override
    public int update(Integer id, Order entity) {
        return orderService.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return orderService.delete(id);
    }

    @Override
    public List<Order> findBySenderClientId(Integer senderClientId) {
        return orderService.findBySenderClientId(senderClientId);
    }

    @Override
    public List<Order> findByReceiverClientId(Integer receiverClientId) {
        return orderService.findByReceiverClientId(receiverClientId);
    }

    @Override
    public List<Order> findByReceiverDepartmentId(Integer receiverDepartmentId) {
        return orderService.findByReceiverDepartmentId(receiverDepartmentId);
    }
}

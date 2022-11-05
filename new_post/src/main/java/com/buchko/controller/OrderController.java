package com.buchko.controller;

import com.buchko.domain.Order;
import java.util.List;

public interface OrderController extends GeneralController<Order, Integer> {
    List<Order> findBySenderClientId(Integer senderClientId);

    List<Order> findByReceiverClientId(Integer receiverClientId);

    List<Order> findByReceiverDepartmentId(Integer receiverDepartmentId);
}

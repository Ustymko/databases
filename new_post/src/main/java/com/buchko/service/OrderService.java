package com.buchko.service;

import com.buchko.domain.Order;
import java.util.List;

public interface OrderService extends GeneralService<Order, Integer>{
    List<Order> findBySenderClientId(Integer senderClientId);

    List<Order> findByReceiverClientId(Integer receiverClientId);

    List<Order> findByReceiverDepartmentId(Integer receiverDepartmentId);
}

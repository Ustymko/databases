package com.buchko.dao;

import com.buchko.domain.Order;
import java.util.List;

public interface OrderDao extends GeneralDao<Order, Integer>{
    List<Order> findBySenderClientId(Integer senderClientId);

    List<Order> findByReceiverClientId(Integer receiverClientId);

    List<Order> findByReceiverDepartmentId(Integer receiverDepartmentId);
}

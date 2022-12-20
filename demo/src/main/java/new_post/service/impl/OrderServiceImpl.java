package new_post.service.impl;

import new_post.domain.OrdersEntity;
import new_post.exception.OrderNotFoundException;
import new_post.repository.OrderRepository;
import new_post.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Override
    public List<OrdersEntity> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrdersEntity findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public OrdersEntity create(OrdersEntity entity) {
        return orderRepository.save(entity);
    }

    @Override
    public void update(Long id, OrdersEntity entity) {
        OrdersEntity order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        order.setDeliveryPrice(entity.getDeliveryPrice());
        order.setParcelPrice(entity.getParcelPrice());
        order.setSenderClient(entity.getSenderClient());
        order.setReceiverClient(entity.getReceiverClient());
        order.setSenderOperator(entity.getSenderOperator());
        order.setReceiverOperator(entity.getReceiverOperator());
        order.setSenderDepartment(entity.getSenderDepartment());
        order.setReceiverDepartment(entity.getReceiverDepartment());
        order.setCourierBetweenDepartments(entity.getCourierBetweenDepartments());
        order.setCourierOnSpot(entity.getCourierOnSpot());
        order.setSendingDatetime(entity.getSendingDatetime());
        order.setReceivingDatetime(entity.getReceivingDatetime());

        orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        OrdersEntity order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));

        orderRepository.deleteById(id);
    }
}

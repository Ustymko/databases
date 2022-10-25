package com.buchko.dao.impl;

import com.buchko.dao.OrderDao;
import com.buchko.domain.Client;
import com.buchko.domain.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class OrderDaoImpl implements OrderDao {

    private static final String FIND_ALL = "SELECT * FROM `order`";
    private static final String FIND_BY_ID = "SELECT * FROM `order` WHERE id = ?";
    private static final String FIND_BY_SENDER_CLIENT_ID = "SELECT * FROM `order` WHERE sender_client_id = ?";
    private static final String FIND_BY_RECEIVER_CLIENT_ID = "SELECT * FROM `order` WHERE receiver_client_id = ?";
    private static final String FIND_BY_RECEIVER_DEPARTMENT_ID = "SELECT * FROM `order` WHERE receiver_department_id = ?";
    private static final String INSERT =
        "INSERT INTO `order`(sender_client_id, receiver_client_id,"
            + "sender_department_id, receiver_department_id,"
            + "sender_operator_id, receiver_operator_id,"
            + "courier_between_departments_id, courier_on_spot_id,"
            + "planned_sending_datetime, planned_receiving_datetime,"
            + "actual_sending_datetime, actual_receiving_datetime,"
            + "parcel_price, delivery_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE =
        "UPDATE `order` SET sender_client_id = ?, receiver_client_id = ?,"
            + "sender_department_id = ?, receiver_department_id = ?,"
            + "sender_operator_id = ?, receiver_operator_id = ?,"
            + "courier_between_departments_id = ?, courier_on_spot_id = ?,"
            + "planned_sending_datetime = ?, planned_receiving_datetime = ?,"
            + "actual_sending_datetime = ?, actual_receiving_datetime = ?,"
            + "parcel_price = ?, delivery_price = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM `order` WHERE id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Order> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Order.class));
    }

    @Override
    public Optional<Order> findById(Integer id) {
        Optional<Order> order;
        try {
            order = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                BeanPropertyRowMapper.newInstance(Order.class), id));
        } catch (EmptyResultDataAccessException e) {
            order = Optional.empty();
        }
        return order;
    }

    @Override
    public int create(Order item) {
        return jdbcTemplate.update(INSERT, item.getSenderClientId(), item.getReceiverClientId(),
            item.getSenderDepartmentId(), item.getReceiverDepartmentId(),
            item.getSenderOperatorId(), item.getReceiverOperatorId(),
            item.getCourierBetweenDepartmentsId(), item.getCourierOnSpotId(),
            item.getPlannedSendingDatetime(), item.getPlannedReceivingDatetime(),
            item.getActualSendingDatetime(), item.getActualReceivingDatetime(),
            item.getParcelPrice(), item.getDeliveryPrice());
    }

    @Override
    public int update(Integer id, Order item) {
        return jdbcTemplate.update(UPDATE, item.getSenderClientId(), item.getReceiverClientId(),
            item.getSenderDepartmentId(), item.getReceiverDepartmentId(),
            item.getSenderOperatorId(), item.getReceiverOperatorId(),
            item.getCourierBetweenDepartmentsId(), item.getCourierOnSpotId(),
            item.getPlannedSendingDatetime(), item.getPlannedReceivingDatetime(),
            item.getActualSendingDatetime(), item.getActualReceivingDatetime(),
            item.getParcelPrice(), item.getDeliveryPrice(),
            id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }


    @Override
    public List<Order> findBySenderClientId(Integer senderClientId) {
        return jdbcTemplate.query(FIND_BY_SENDER_CLIENT_ID,
            BeanPropertyRowMapper.newInstance(Order.class), senderClientId);
    }

    @Override
    public List<Order> findByReceiverClientId(Integer receiverClientId) {
        return jdbcTemplate.query(FIND_BY_RECEIVER_CLIENT_ID,
            BeanPropertyRowMapper.newInstance(Order.class), receiverClientId);
    }

    @Override
    public List<Order> findByReceiverDepartmentId(Integer receiverDepartmentId) {
        return jdbcTemplate.query(FIND_BY_RECEIVER_DEPARTMENT_ID,
            BeanPropertyRowMapper.newInstance(Order.class), receiverDepartmentId);
    }
}

package com.buchko.dao.impl;

import com.buchko.dao.CourierDao;
import com.buchko.domain.Courier;
import com.buchko.domain.Operator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CourierDaoImpl implements CourierDao {

    private static final String FIND_ALL = "SELECT * FROM `courier`";
    private static final String FIND_BY_ID = "SELECT * FROM `courier` WHERE id = ?";
    private static final String FIND_BY_DEPARTMENT = "SELECT * FROM `courier` WHERE department_id = ?";
    private static final String INSERT =
        "INSERT INTO `courier`(department_id, name, surname, phoneNumber) VALUES (?, ?, ?, ?)";
    private static final String UPDATE =
        "UPDATE `courier` SET department_id = ?, name = ?, surname = ?, phoneNumber = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM `courier` WHERE id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Courier> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Courier.class));

    }

    @Override
    public Optional<Courier> findById(Integer id) {
        Optional<Courier> courier;
        try {
            courier = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                BeanPropertyRowMapper.newInstance(Courier.class), id));
        } catch (EmptyResultDataAccessException e) {
            courier = Optional.empty();
        }
        return courier;
    }

    @Override
    public int create(Courier item) {
        return jdbcTemplate.update(INSERT, item.getDepartmentId(), item.getName(),
            item.getSurname(), item.getPhoneNumber());
    }

    @Override
    public int update(Integer id, Courier item) {
        return jdbcTemplate.update(UPDATE, item.getDepartmentId(), item.getName(),
            item.getSurname(), item.getPhoneNumber(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }


    @Override
    public List<Courier> findByDepartmentId(Integer departmentId) {
        return jdbcTemplate.query(FIND_BY_DEPARTMENT,
            BeanPropertyRowMapper.newInstance(Courier.class), departmentId);
    }
}

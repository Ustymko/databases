package com.buchko.dao.impl;

import com.buchko.dao.OperatorDao;
import com.buchko.domain.Client;
import com.buchko.domain.Operator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class OperatorDaoImpl implements OperatorDao {

    private static final String FIND_ALL = "SELECT * FROM `operator`";
    private static final String FIND_BY_ID = "SELECT * FROM `operator` WHERE id = ?";
    private static final String FIND_BY_DEPARTMENT = "SELECT * FROM `operator` WHERE department_id = ?";
    private static final String INSERT =
        "INSERT INTO `operator`(department_id, name, surname, phone_number) VALUES (?, ?, ?, ?)";
    private static final String UPDATE =
        "UPDATE `operator` SET department_id = ?, name = ?, surname = ?, phone_number = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM `operator` WHERE id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Operator> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Operator.class));

    }

    @Override
    public Optional<Operator> findById(Integer id) {
        Optional<Operator> operator;
        try {
            operator = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                BeanPropertyRowMapper.newInstance(Operator.class), id));
        } catch (EmptyResultDataAccessException e) {
            operator = Optional.empty();
        }
        return operator;
    }

    @Override
    public int create(Operator item) {
        return jdbcTemplate.update(INSERT, item.getDepartmentId(), item.getName(),
            item.getSurname(),
            item.getPhoneNumber());
    }

    @Override
    public int update(Integer id, Operator item) {
        return jdbcTemplate.update(UPDATE, item.getDepartmentId(), item.getName(),
            item.getSurname(), item.getPhoneNumber(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }


    @Override
    public List<Operator> findByDepartmentId(Integer departmentId) {
        return jdbcTemplate.query(FIND_BY_DEPARTMENT,
            BeanPropertyRowMapper.newInstance(Operator.class), departmentId);
    }
}

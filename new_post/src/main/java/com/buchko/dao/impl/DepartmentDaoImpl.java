package com.buchko.dao.impl;

import com.buchko.dao.DepartmentDao;
import com.buchko.domain.Department;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    private static final String FIND_ALL = "SELECT * FROM `department`";
    private static final String FIND_BY_ID = "SELECT * FROM `department` WHERE id = ?";
    private static final String FIND_BY_NUMBER = "SELECT * FROM `department` WHERE number = ?";
    private static final String FIND_BY_CITY_ID = "SELECT * FROM `department` WHERE city_id = ?";
    private static final String INSERT =
        "INSERT INTO `department`(city_id, address, number) VALUES (?, ?, ?)";
    private static final String UPDATE =
        "UPDATE `department` SET city_id = ?, address = ?, number = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM `department` WHERE id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Department> findByNumber(Integer number) {
        return jdbcTemplate.query(FIND_BY_NUMBER,
            BeanPropertyRowMapper.newInstance(Department.class), number);
    }

    @Override
    public List<Department> findByCityId(Integer cityId) {
        return jdbcTemplate.query(FIND_BY_CITY_ID,
            BeanPropertyRowMapper.newInstance(Department.class), cityId);
    }

    @Override
    public List<Department> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Department.class));
    }

    @Override
    public Optional<Department> findById(Integer id) {
        Optional<Department> department;
        try {
            department = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                BeanPropertyRowMapper.newInstance(Department.class), id));
        } catch (EmptyResultDataAccessException e) {
            department = Optional.empty();
        }
        return department;
    }

    @Override
    public int create(Department item) {
        return jdbcTemplate.update(INSERT, item.getCityId(), item.getAddress(), item.getNumber());
    }

    @Override
    public int update(Integer id, Department item) {
        return jdbcTemplate.update(UPDATE, item.getCityId(), item.getAddress(), item.getNumber(),
            id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }


}

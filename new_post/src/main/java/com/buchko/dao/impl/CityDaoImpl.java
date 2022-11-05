package com.buchko.dao.impl;

import com.buchko.dao.CityDao;
import com.buchko.domain.City;
import com.buchko.domain.Client;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CityDaoImpl implements CityDao {

    private static final String FIND_ALL = "SELECT * FROM `city`";
    private static final String FIND_BY_ID = "SELECT * FROM `city` WHERE id = ?";
    private static final String FIND_BY_REGION_NAME = "SELECT * FROM `city` WHERE region_name = ?";
    private static final String INSERT =
        "INSERT INTO `city`(city, region_name) VALUES (?, ?)";
    private static final String UPDATE =
        "UPDATE `city` SET city = ?, region_name = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM `city` WHERE id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<City> findCitiesByRegionName(String regionName) {
        return jdbcTemplate.query(FIND_BY_REGION_NAME,
            BeanPropertyRowMapper.newInstance(City.class), regionName);
    }

    @Override
    public List<City> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(City.class));
    }

    @Override
    public Optional<City> findById(Integer id) {
        Optional<City> city;
        try {
            city = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                BeanPropertyRowMapper.newInstance(City.class), id));
        } catch (EmptyResultDataAccessException e) {
            city = Optional.empty();
        }
        return city;
    }

    @Override
    public int create(City item) {
        return jdbcTemplate.update(INSERT, item.getCity(), item.getRegionName());
    }

    @Override
    public int update(Integer id, City item) {
        return jdbcTemplate.update(UPDATE, item.getCity(), item.getRegionName(),
            id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }


}

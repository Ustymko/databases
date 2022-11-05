package com.buchko.dao.impl;

import com.buchko.dao.RegionDao;
import com.buchko.domain.Client;
import com.buchko.domain.Region;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class RegionDaoImpl implements RegionDao {
    private static final String FIND_ALL = "SELECT * FROM `region`";
    private static final String FIND_BY_REGION = "SELECT * FROM `region` WHERE region = ?";
    private static final String INSERT =
        "INSERT INTO `region`(region) VALUES (?)";
    private static final String UPDATE =
        "UPDATE `region` SET region = ? WHERE region = ?";
    private static final String DELETE = "DELETE FROM `region` WHERE region = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Region> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Region.class));
    }

    @Override
    public Optional<Region> findById(String id) {
        Optional<Region> region;
        try {
            region = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_REGION,
                BeanPropertyRowMapper.newInstance(Region.class), id));
        } catch (EmptyResultDataAccessException e) {
            region = Optional.empty();
        }
        return region;
    }

    @Override
    public int create(Region item) {
        return jdbcTemplate.update(INSERT, item.getRegion());
    }

    @Override
    public int update(String id, Region item) {
        return jdbcTemplate.update(UPDATE, item.getRegion(), id);
    }

    @Override
    public int delete(String id) {
        return jdbcTemplate.update(DELETE, id);
    }

}

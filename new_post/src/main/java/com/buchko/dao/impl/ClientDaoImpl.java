package com.buchko.dao.impl;

import com.buchko.dao.ClientDao;
import com.buchko.domain.Client;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ClientDaoImpl implements ClientDao {

    private static final String FIND_ALL = "SELECT * FROM `client`";
    private static final String FIND_BY_ID = "SELECT * FROM `client` WHERE id = ?";
    private static final String INSERT =
        "INSERT INTO `client`(name, surname, phone_number) VALUES (?, ?, ?)";
    private static final String UPDATE =
        "UPDATE `client` SET name = ?, surname = ?, phone_number = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM `client` WHERE id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Client> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Client.class));
    }

    @Override
    public Optional<Client> findById(Integer id) {
        Optional<Client> client;
        try {
            client = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                BeanPropertyRowMapper.newInstance(Client.class), id));
        } catch (EmptyResultDataAccessException e) {
            client = Optional.empty();
        }
        return client;
    }

    @Override
    public int create(Client item) {
        return jdbcTemplate.update(INSERT, item.getName(), item.getSurname(),
            item.getPhoneNumber());
    }

    @Override
    public int update(Integer id, Client item) {
        return jdbcTemplate.update(UPDATE, item.getName(), item.getSurname(), item.getPhoneNumber(),
            id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }


}

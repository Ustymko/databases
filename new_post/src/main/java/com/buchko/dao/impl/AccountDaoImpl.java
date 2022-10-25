package com.buchko.dao.impl;

import com.buchko.dao.AccountDao;
import com.buchko.domain.Account;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class AccountDaoImpl implements AccountDao {

    private static final String FIND_ALL = "SELECT * FROM `account`";
    private static final String FIND_BY_ID = "SELECT * FROM `account` WHERE client_id = ?";
    private static final String INSERT =
        "INSERT INTO `account`(client_id, username, password, emailAddress) VALUES (?, ?, ?)";
    private static final String UPDATE =
        "UPDATE `account` SET username = ?, password = ?, emailAddress = ? WHERE client_id = ?";
    private static final String DELETE = "DELETE FROM `account` WHERE client_id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Account.class));
    }

    @Override
    public Optional<Account> findById(Integer id) {
        Optional<Account> account;
        try {
            account = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                BeanPropertyRowMapper.newInstance(Account.class), id));
        } catch (EmptyResultDataAccessException e) {
            account = Optional.empty();
        }
        return account;
    }

    @Override
    public int create(Account item) {
        return jdbcTemplate.update(INSERT, item.getUsername(), item.getPassword(),
            item.getEmailAddress());
    }

    @Override
    public int update(Integer id, Account item) {
        return jdbcTemplate.update(UPDATE, item.getUsername(), item.getPassword(),
            item.getEmailAddress(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }


}

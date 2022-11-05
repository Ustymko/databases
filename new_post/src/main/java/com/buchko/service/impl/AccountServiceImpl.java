package com.buchko.service.impl;

import com.buchko.dao.AccountDao;
import com.buchko.domain.Account;
import com.buchko.service.AccountService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return accountDao.findById(id);
    }

    @Override
    public int create(Account entity) {
        return accountDao.create(entity);
    }

    @Override
    public int update(Integer id, Account entity) {
        return accountDao.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return accountDao.delete(id);
    }
}

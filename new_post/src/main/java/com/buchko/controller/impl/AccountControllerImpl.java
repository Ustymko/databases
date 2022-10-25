package com.buchko.controller.impl;

import com.buchko.controller.AccountController;
import com.buchko.domain.Account;
import com.buchko.service.AccountService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountControllerImpl implements AccountController {

    @Autowired
    AccountService accountService;

    @Override
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return accountService.findById(id);
    }

    @Override
    public int create(Account entity) {
        return accountService.create(entity);
    }

    @Override
    public int update(Integer id, Account entity) {
        return accountService.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return accountService.delete(id);
    }
}

package com.buchko.service.impl;

import com.buchko.dao.ClientDao;
import com.buchko.domain.Client;
import com.buchko.service.ClientService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Override
    public List<Client> findAll() {
        return clientDao.findAll();
    }

    @Override
    public Optional<Client> findById(Integer id) {
        return clientDao.findById(id);
    }

    @Override
    public int create(Client entity) {
        return clientDao.create(entity);
    }

    @Override
    public int update(Integer id, Client entity) {
        return clientDao.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return clientDao.delete(id);
    }
}

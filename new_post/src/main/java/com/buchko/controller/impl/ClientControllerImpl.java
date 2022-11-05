package com.buchko.controller.impl;

import com.buchko.controller.ClientController;
import com.buchko.domain.Client;
import com.buchko.service.ClientService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientControllerImpl implements ClientController {

    @Autowired
    ClientService clientService;

    @Override
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @Override
    public Optional<Client> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public int create(Client entity) {
        return clientService.create(entity);
    }

    @Override
    public int update(Integer id, Client entity) {
        return clientService.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return clientService.delete(id);
    }
}

package new_post.service.impl;

import new_post.domain.ClientEntity;
import new_post.exception.ClientNotFoundException;
import new_post.repository.ClientRepository;
import new_post.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<ClientEntity> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public ClientEntity findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }

    @Override
    public ClientEntity create(ClientEntity entity) {
        return clientRepository.save(entity);
    }

    @Override
    public void update(Long id, ClientEntity entity) {
        ClientEntity client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        client.setName(entity.getName());
        client.setSurname(entity.getSurname());
        client.setPhoneNumber(entity.getPhoneNumber());
        client.setAddress(entity.getAddress());

        clientRepository.save(client);

    }

    @Override
    public void delete(Long id) {
        ClientEntity client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void insertTenClients() {
        clientRepository.insertTenClients();
    }

    @Override
    @Transactional
    public void insertClient(ClientEntity client) {
        clientRepository.insertClientWithProcedure(client.getName(),
                client.getSurname(), client.getPhoneNumber(), client.getAddress());
    }
}

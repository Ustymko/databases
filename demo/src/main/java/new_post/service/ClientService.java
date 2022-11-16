package new_post.service;


import new_post.domain.ClientEntity;

public interface ClientService extends GeneralService<ClientEntity, Long>{
    void insertTenClients();

    void insertClient(ClientEntity client);
}

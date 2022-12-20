package new_post.repository;

import new_post.domain.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    @Procedure("insert_client")
    void insertClientWithProcedure(String name, String surname, String phone_number, String address);

    @Procedure("insert_10_clients")
    void insertTenClients();
}

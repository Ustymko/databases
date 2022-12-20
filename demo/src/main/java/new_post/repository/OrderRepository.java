package new_post.repository;

import new_post.domain.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrdersEntity, Long> {
    @Procedure("find_max_parcel_price_for_client")
    void findMaxParcelPriceForClient(Integer receiverClientId);
}

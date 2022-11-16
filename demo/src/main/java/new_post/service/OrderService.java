package new_post.service;

import new_post.domain.OrdersEntity;

public interface OrderService extends GeneralService<OrdersEntity, Long> {
    void findMaxParcelPriceForClient(Integer receiverClientId);
}

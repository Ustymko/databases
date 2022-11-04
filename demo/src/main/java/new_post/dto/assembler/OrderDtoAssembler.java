package new_post.dto.assembler;

import new_post.controller.OrderController;
import new_post.domain.OrdersEntity;
import new_post.dto.OrderDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class OrderDtoAssembler implements RepresentationModelAssembler<OrdersEntity, OrderDto> {
    @Override
    public OrderDto toModel(OrdersEntity entity) {
        OrderDto orderDto = OrderDto.builder()
                .id(entity.getId())
                .parcelPrice(entity.getParcelPrice())
                .deliveryPrice(entity.getDeliveryPrice())
                .senderClient(entity.getSenderClient())
                .receiverClient(entity.getReceiverClient())
                .senderOperator(entity.getSenderOperator())
                .receiverOperator(entity.getReceiverOperator())
                .senderDepartment(entity.getSenderDepartment())
                .receiverDepartment(entity.getReceiverDepartment())
                .courierBetweenDepartments(entity.getCourierBetweenDepartments())
                .courierOnSpot(entity.getCourierOnSpot())
                .sendingDatetime(entity.getSendingDatetime())
                .receivingDatetime(entity.getReceivingDatetime())
                .build();
        Link selfLink = linkTo(methodOn(OrderController.class).getOrder(entity.getId())).withSelfRel();
        orderDto.add(selfLink);
        return orderDto;
    }

    @Override
    public CollectionModel<OrderDto> toCollectionModel(Iterable<? extends OrdersEntity> entities) {
        CollectionModel<OrderDto> orderDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(OrderController.class).getAllOrders()).withSelfRel();
        orderDtos.add(selfLink);
        return orderDtos;
    }
}

package new_post.controller;

import new_post.domain.OrdersEntity;
import new_post.dto.OrderDto;
import new_post.dto.assembler.OrderDtoAssembler;
import new_post.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderDtoAssembler orderDtoAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<OrderDto>> getAllOrders(){
        List<OrdersEntity> orders = orderService.findAll();
        CollectionModel<OrderDto> orderDtos = orderDtoAssembler.toCollectionModel(orders);
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId){
        OrdersEntity order = orderService.findById(orderId);
        OrderDto orderDto = orderDtoAssembler.toModel(order);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrdersEntity order) {
        OrdersEntity newOrder = orderService.create(order);
        OrderDto orderDto = orderDtoAssembler.toModel(newOrder);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{orderId}")
    public ResponseEntity<?> updateOrder(@RequestBody OrdersEntity order, @PathVariable Long orderId) {
        orderService.update(orderId, order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        orderService.delete(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findMaxPriceByReceiverClientId/{receiverClientId}")
    public void findMaxParcelPriceForClient(@PathVariable Integer receiverClientId){
        orderService.findMaxParcelPriceForClient(receiverClientId);
    }
}

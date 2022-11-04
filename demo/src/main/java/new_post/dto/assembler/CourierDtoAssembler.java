package new_post.dto.assembler;

import new_post.controller.CourierController;
import new_post.domain.CourierEntity;
import new_post.dto.CourierDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CourierDtoAssembler implements RepresentationModelAssembler<CourierEntity, CourierDto> {
    @Override
    public CourierDto toModel(CourierEntity entity) {
        CourierDto courierDto = CourierDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .phoneNumber(entity.getPhoneNumber())
                .department(entity.getDepartment())
                .build();
        Link selfLink = linkTo(methodOn(CourierController.class).getCourier(entity.getId())).withSelfRel();
        courierDto.add(selfLink);
        return courierDto;
    }

    @Override
    public CollectionModel<CourierDto> toCollectionModel(Iterable<? extends CourierEntity> entities) {
        CollectionModel<CourierDto> courierDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CourierController.class).getAllCouriers()).withSelfRel();
        courierDtos.add(selfLink);
        return courierDtos;
    }
}

package new_post.dto.assembler;

import new_post.controller.OperatorController;
import new_post.domain.OperatorEntity;
import new_post.dto.OperatorDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class OperatorDtoAssembler implements RepresentationModelAssembler<OperatorEntity, OperatorDto> {
    @Override
    public OperatorDto toModel(OperatorEntity entity) {
        OperatorDto operatorDto = OperatorDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .phoneNumber(entity.getPhoneNumber())
                .department(entity.getDepartment())
                .build();
        Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(OperatorController.class).getOperator(entity.getId())).withSelfRel();
        operatorDto.add(selfLink);
        return operatorDto;
    }

    @Override
    public CollectionModel<OperatorDto> toCollectionModel(Iterable<? extends OperatorEntity> entities) {
        CollectionModel<OperatorDto> operatorDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(OperatorController.class).getAllOperators()).withSelfRel();
        operatorDtos.add(selfLink);
        return operatorDtos;
    }
}

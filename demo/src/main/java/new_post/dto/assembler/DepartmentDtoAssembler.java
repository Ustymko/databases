package new_post.dto.assembler;

import new_post.controller.DepartmentController;
import new_post.domain.DepartmentEntity;
import new_post.dto.DepartmentDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class DepartmentDtoAssembler implements RepresentationModelAssembler<DepartmentEntity, DepartmentDto> {

    @Override
    public DepartmentDto toModel(DepartmentEntity entity) {
        DepartmentDto departmentDto = DepartmentDto.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .address(entity.getAddress())
                .city(entity.getCity())
                .region(entity.getRegion())
                .build();
        Link selfLink = linkTo(methodOn(DepartmentController.class).getDepartment(entity.getId())).withSelfRel();
        departmentDto.add(selfLink);
        return departmentDto;
    }

    @Override
    public CollectionModel<DepartmentDto> toCollectionModel(Iterable<? extends DepartmentEntity> entities) {
        CollectionModel<DepartmentDto> departmentDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(DepartmentController.class).getAllDepartments()).withSelfRel();
        departmentDtos.add(selfLink);
        return departmentDtos;
    }
}

package new_post.dto.assembler;

import new_post.controller.ClientController;
import new_post.domain.ClientEntity;
import new_post.dto.ClientDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClientDtoAssembler implements RepresentationModelAssembler<ClientEntity, ClientDto> {

    @Override
    public ClientDto toModel(ClientEntity entity) {
        ClientDto clientDto = ClientDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .phoneNumber(entity.getPhoneNumber())
                .address(entity.getAddress())
                .build();
        Link selfLink = linkTo(methodOn(ClientController.class).getClient(entity.getId())).withSelfRel();
        clientDto.add(selfLink);
        return clientDto;
    }

    @Override
    public CollectionModel<ClientDto> toCollectionModel(Iterable<? extends ClientEntity> entities) {
        CollectionModel<ClientDto> bookDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ClientController.class).getAllClients()).withSelfRel();
        bookDtos.add(selfLink);
        return bookDtos;
    }
}

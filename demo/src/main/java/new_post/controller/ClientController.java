package new_post.controller;

import new_post.domain.ClientEntity;
import new_post.dto.ClientDto;
import new_post.dto.assembler.ClientDtoAssembler;
import new_post.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @Autowired
    ClientDtoAssembler clientDtoAssembler;


    @GetMapping
    public ResponseEntity<CollectionModel<ClientDto>> getAllClients(){
        List<ClientEntity> clients = clientService.findAll();
        CollectionModel<ClientDto> clientDtos = clientDtoAssembler.toCollectionModel(clients);
        return new ResponseEntity<>(clientDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{clientId}")
    public ResponseEntity<ClientDto> getClient(@PathVariable Long clientId){
        ClientEntity client = clientService.findById(clientId);
        ClientDto clientDto =clientDtoAssembler.toModel(client);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ClientDto> addClient(@RequestBody ClientEntity client) {
        ClientEntity newClient = clientService.create(client);
        ClientDto clientDto = clientDtoAssembler.toModel(newClient);
        return new ResponseEntity<>(clientDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{clientId}")
    public ResponseEntity<?> updateClient(@RequestBody ClientEntity client, @PathVariable Long clientId) {
        clientService.update(clientId, client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Long clientId) {
        clientService.delete(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/add10")
    public void createTenClients(){
        clientService.insertTenClients();
    }

    @PostMapping(value = "/addWithProcedure")
    public void addClientWithProc(@RequestBody ClientEntity client){
        clientService.insertClient(client);
    }


}

package new_post.controller;

import new_post.domain.CourierEntity;
import new_post.dto.CourierDto;
import new_post.dto.assembler.CourierDtoAssembler;
import new_post.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/courier")
public class CourierController {
    @Autowired
    CourierService courierService;

    @Autowired
    CourierDtoAssembler courierDtoAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<CourierDto>> getAllCouriers(){
        List<CourierEntity> couriers = courierService.findAll();
        CollectionModel<CourierDto> courierDtos = courierDtoAssembler.toCollectionModel(couriers);
        return new ResponseEntity<>(courierDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{courierId}")
    public ResponseEntity<CourierDto> getCourier(@PathVariable Long courierId){
        CourierEntity courier = courierService.findById(courierId);
        CourierDto courierDto = courierDtoAssembler.toModel(courier);
        return new ResponseEntity<>(courierDto, HttpStatus.OK);
    }

    @GetMapping(value = "/byDepartment/{departmentId}")
    public ResponseEntity<CollectionModel<CourierDto>> getCouriersByDepartmentId(@PathVariable Long departmentId){
        List<CourierEntity> couriers = courierService.getByDepartmentId(departmentId);
        CollectionModel<CourierDto> courierDtos = courierDtoAssembler.toCollectionModel(couriers);
        return new ResponseEntity<>(courierDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CourierDto> addCourier(@RequestBody CourierEntity courier) {
        CourierEntity newCourier = courierService.create(courier);
        CourierDto courierDto = courierDtoAssembler.toModel(newCourier);
        return new ResponseEntity<>(courierDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{courierId}")
    public ResponseEntity<?> updateCourier(@RequestBody CourierEntity courier, @PathVariable Long courierId) {
        courierService.update(courierId, courier);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{courierId}")
    public ResponseEntity<?> deleteCourier(@PathVariable Long courierId) {
        courierService.delete(courierId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

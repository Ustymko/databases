package new_post.controller;

import new_post.domain.OperatorEntity;
import new_post.dto.OperatorDto;
import new_post.dto.assembler.OperatorDtoAssembler;
import new_post.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/operator")
public class OperatorController {
    @Autowired
    OperatorService operatorService;

    @Autowired
    OperatorDtoAssembler operatorDtoAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<OperatorDto>> getAllOperators(){
        List<OperatorEntity> operators = operatorService.findAll();
        CollectionModel<OperatorDto> operatorDtos = operatorDtoAssembler.toCollectionModel(operators);
        return new ResponseEntity<>(operatorDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{operatorId}")
    public ResponseEntity<OperatorDto> getOperator(@PathVariable Long operatorId){
        OperatorEntity operator = operatorService.findById(operatorId);
        OperatorDto operatorDto = operatorDtoAssembler.toModel(operator);
        return new ResponseEntity<>(operatorDto, HttpStatus.OK);
    }

    @GetMapping(value = "/byDepartment/{departmentId}")
    public ResponseEntity<CollectionModel<OperatorDto>> getOperatorsByDepartmentId(@PathVariable Long departmentId){
        List<OperatorEntity> operators = operatorService.getByDepartmentId(departmentId);
        CollectionModel<OperatorDto> operatorDtos = operatorDtoAssembler.toCollectionModel(operators);
        return new ResponseEntity<>(operatorDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<OperatorDto> addOperator(@RequestBody OperatorEntity operator) {
        OperatorEntity newOperator = operatorService.create(operator);
        OperatorDto operatorDto = operatorDtoAssembler.toModel(newOperator);
        return new ResponseEntity<>(operatorDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{operatorId}")
    public ResponseEntity<?> updateOperator(@RequestBody OperatorEntity operator, @PathVariable Long operatorId) {
        operatorService.update(operatorId, operator);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{operatorId}")
    public ResponseEntity<?> deleteOperator(@PathVariable Long operatorId) {
        operatorService.delete(operatorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

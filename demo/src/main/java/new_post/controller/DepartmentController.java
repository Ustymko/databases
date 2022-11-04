package new_post.controller;


import new_post.domain.DepartmentEntity;
import new_post.dto.DepartmentDto;
import new_post.dto.assembler.DepartmentDtoAssembler;
import new_post.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/controller")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @Autowired
    DepartmentDtoAssembler departmentDtoAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<DepartmentDto>> getAllDepartments(){
        List<DepartmentEntity> departments = departmentService.findAll();
        CollectionModel<DepartmentDto> departmentDtos = departmentDtoAssembler.toCollectionModel(departments);
        return new ResponseEntity<>(departmentDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{departmentId}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable Long departmentId){
        DepartmentEntity department = departmentService.findById(departmentId);
        DepartmentDto departmentDto = departmentDtoAssembler.toModel(department);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentEntity department) {
        DepartmentEntity newDepartment = departmentService.create(department);
        DepartmentDto departmentDto = departmentDtoAssembler.toModel(newDepartment);
        return new ResponseEntity<>(departmentDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{departmentId}")
    public ResponseEntity<?> updateDepartment(@RequestBody DepartmentEntity department, @PathVariable Long departmentId) {
        departmentService.update(departmentId, department);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{departmentId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long departmentId) {
        departmentService.delete(departmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

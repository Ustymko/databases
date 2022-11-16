package new_post.service.impl;

import new_post.domain.DepartmentEntity;
import new_post.exception.DepartmentNotFoundException;
import new_post.repository.DepartmentRepository;
import new_post.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentEntity> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public DepartmentEntity findById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    @Override
    public DepartmentEntity create(DepartmentEntity entity) {
        return departmentRepository.save(entity);
    }

    @Override
    public void update(Long id, DepartmentEntity entity) {
        DepartmentEntity department = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
        department.setAddress(entity.getAddress());
        department.setCity(entity.getCity());
        department.setNumber(entity.getNumber());
        department.setRegion(entity.getRegion());
        departmentRepository.save(department);
    }

    @Override
    public void delete(Long id) {
        DepartmentEntity department = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
        departmentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void dynamic_procedure() {
        departmentRepository.dynamic_procedure();
    }
}

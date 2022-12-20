package new_post.service.impl;


import new_post.domain.OperatorEntity;
import new_post.exception.OperatorNotFoundException;
import new_post.repository.OperatorRepository;
import new_post.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    OperatorRepository operatorRepository;

    @Override
    public List<OperatorEntity> findAll() {
        return operatorRepository.findAll();
    }

    @Override
    public OperatorEntity findById(Long id) {
        return operatorRepository.findById(id).orElseThrow(() -> new OperatorNotFoundException(id));
    }

    @Override
    public OperatorEntity create(OperatorEntity entity) {
        return operatorRepository.save(entity);
    }

    @Override
    public void update(Long id, OperatorEntity entity) {
        OperatorEntity operator = operatorRepository.findById(id).orElseThrow(() -> new OperatorNotFoundException(id));
        operator.setName(entity.getName());
        operator.setSurname(entity.getSurname());
        operator.setPhoneNumber(entity.getPhoneNumber());
        operator.setDepartment(entity.getDepartment());

        operatorRepository.save(operator);
    }

    @Override
    public void delete(Long id) {
        OperatorEntity operator = operatorRepository.findById(id).orElseThrow(() -> new OperatorNotFoundException(id));
        operatorRepository.deleteById(id);
    }

    @Override
    public List<OperatorEntity> getByDepartmentId(Long departmentId) {
        return operatorRepository.getByDepartmentId(departmentId);
    }
}

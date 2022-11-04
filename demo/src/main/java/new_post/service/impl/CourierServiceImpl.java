package new_post.service.impl;

import new_post.domain.CourierEntity;
import new_post.exception.CourierNotFoundException;
import new_post.repository.CourierRepository;
import new_post.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierServiceImpl implements CourierService {

    @Autowired
    CourierRepository courierRepository;
    @Override
    public List<CourierEntity> findAll() {
        return courierRepository.findAll();
    }

    @Override
    public CourierEntity findById(Long id) {
        return courierRepository.findById(id).orElseThrow(() -> new CourierNotFoundException(id));
    }

    @Override
    public CourierEntity create(CourierEntity entity) {
        return courierRepository.save(entity);
    }

    @Override
    public void update(Long id, CourierEntity entity) {
        CourierEntity courier = courierRepository.findById(id).orElseThrow(() -> new CourierNotFoundException(id));
        courier.setDepartment(entity.getDepartment());
        courier.setName(entity.getName());
        courier.setSurname(entity.getSurname());
        courier.setPhoneNumber(entity.getPhoneNumber());

        courierRepository.save(courier);
    }

    @Override
    public void delete(Long id) {
        CourierEntity courier = courierRepository.findById(id).orElseThrow(() -> new CourierNotFoundException(id));
        courierRepository.deleteById(id);
    }

    @Override
    public List<CourierEntity> getByDepartmentId(Long departmentId) {
        return courierRepository.getByDepartmentId(departmentId);
    }
}

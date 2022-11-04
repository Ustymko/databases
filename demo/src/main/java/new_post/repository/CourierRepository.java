package new_post.repository;

import new_post.domain.CourierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourierRepository extends JpaRepository<CourierEntity, Long> {

    @Query(value = "SELECT * FROM operator op WHERE op.department_id = :departmentId", nativeQuery = true)
    List<CourierEntity> getByDepartmentId(@Param("departmentId") Long departmentId);
}

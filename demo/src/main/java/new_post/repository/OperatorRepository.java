package new_post.repository;

import new_post.domain.OperatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperatorRepository extends JpaRepository<OperatorEntity, Long> {

    @Query(value = "SELECT * FROM operator op WHERE op.department_id = :departmentId", nativeQuery = true)
    List<OperatorEntity> getByDepartmentId(@Param("departmentId") Long departmentId);

}

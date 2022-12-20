package new_post.service;

import java.util.List;

public interface WorkerService<T, ID> extends GeneralService<T, ID>{
    List<T> getByDepartmentId(Long departmentId);
}

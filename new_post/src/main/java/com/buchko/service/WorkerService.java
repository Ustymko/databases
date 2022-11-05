package com.buchko.service;

import java.util.List;

public interface WorkerService<T, ID> extends GeneralService<T, ID>{
    List<T> findByDepartmentId(ID departmentId);
}

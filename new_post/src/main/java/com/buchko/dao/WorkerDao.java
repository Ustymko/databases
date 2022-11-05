package com.buchko.dao;

import java.util.List;

public interface WorkerDao<T, ID> extends GeneralDao<T, ID>{
    List<T> findByDepartmentId(ID departmentId);
}

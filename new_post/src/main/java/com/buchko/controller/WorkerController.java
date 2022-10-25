package com.buchko.controller;

import java.util.List;

public interface WorkerController<T, ID> extends GeneralController<T, ID> {
    List<T> findByDepartmentId(ID departmentId);
}

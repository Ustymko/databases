package com.buchko.dao;

import java.util.List;
import java.util.Optional;

public interface GeneralDao<T, ID> {

    List<T> findAll();

    Optional<T> findById(ID id);

    int create(T item);

    int update(ID id, T item);

    int delete(ID id);
}

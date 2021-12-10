package com.instacafe.moments.service;

import com.instacafe.moments.model.AbstractEntity;

import java.util.List;
import java.util.UUID;

public interface EntityService<T extends AbstractEntity> {
    T save(T photo);
    List<T> saveAll(Iterable<T> ts);
    T update(T t);
    T findById(UUID id);
    List<T> findAll();
    void deleteById(UUID id);
}

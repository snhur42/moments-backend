package com.instacafe.moments.service.photo_session;

import com.instacafe.moments.model.AbstractEntity;

import java.util.List;
import java.util.UUID;

public interface BusinessService<T extends AbstractEntity> {
    T save(T t);
    T update(T t);
    List<T> saveAll(Iterable<T> ts);
    T findById(UUID id);
    List<T> findAll();
    void deleteById(UUID id);
}

package com.instacafe.moments.service.photo_session;

import com.instacafe.moments.model.AbstractEntity;
import com.instacafe.moments.repository.EntityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class BusinessServiceImpl<T extends AbstractEntity, R extends EntityRepository<T>> implements BusinessService<T> {
    protected final R repository;

    public BusinessServiceImpl(R r) {
        super();
        this.repository = r;
    }

    @Override
    public T save(T t) {
        return repository.save(t);
    }

    @Override
    public T update(T t) {
        return repository.save(t);
    }

    @Override
    public List<T> saveAll(Iterable<T> ts) {
        return repository.saveAll(ts);
    }

    @Override
    public T findById(UUID id) {
        return repository.findById(id).get();
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}

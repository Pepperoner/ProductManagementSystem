package com.management.product.service;

import com.management.product.entity.Model;
import com.management.product.repository.DataRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

public abstract class DataServiceImpl<T extends Model> implements DataService<T> {

    private DataRepository<T> repository;

    public DataServiceImpl(DataRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public T add(T model) throws IllegalArgumentException {
        if (model == null) {
            throw new IllegalArgumentException("Trying to save null");
        }
        return repository.save(model);
    }

    @Override
    @Transactional
    public T update(T model) {
        return this.add(model);
    }

    @Override
    public Collection<T> addAll(Collection<T> collection) {
        Collection<T> addedModels = new ArrayList<T>();
        if (collection != null) {
            collection.forEach(model -> addedModels.add(this.add(model)));
        }
        return addedModels;
    }

    @Override
    public Collection<T> updateAll(Collection<T> collection) {
        return this.addAll(collection);
    }

    @Override
    @Transactional(readOnly = true)
    public T get(long id) throws NullPointerException {
        T foundedModel = repository.findOne(id);
        if (foundedModel == null) {
            throw new NullPointerException("Can`t find model with such id = " + id);
        }
        return foundedModel;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<T> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void remove(long id) {
        repository.delete(id);
    }

    @Override
    public void remove(T model) throws IllegalArgumentException {
        if (model == null) {
            throw new IllegalArgumentException("Trying to delete null");
        }
        repository.delete(model);
    }

    @Override
    public void remove(Collection<T> collection) throws IllegalArgumentException {
        if (collection == null) {
            throw new IllegalArgumentException("Collection is empty (null)");
        }
        collection.forEach(this::remove);
        /*for (T t : collection) {
            this.remove(t);
        }*/
    }

    @Override
    @Transactional
    public void removeAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exist(long id) {
        return repository.exists(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exist(T model) throws IllegalArgumentException{
        if (model == null){
            throw new IllegalArgumentException("Trying to check exiting null`");
        }
        return this.exist(model.getId());
    }
}

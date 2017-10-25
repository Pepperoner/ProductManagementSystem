package com.management.product.service;

import com.management.product.entity.Model;

import java.util.Collection;

public interface DataService<T extends Model> {

    T add(T t);

    Collection<T> addAll(Collection<T> collection);

    T update(T t);

    Collection<T> updateAll(Collection<T> collection);

    T get(long id);

    Collection<T> getAll();

    void remove(long id);

    void remove(T t);

    void remove(Collection<T> collection);

    void removeAll();

    boolean exist(long id);

    boolean exist(T t);
}

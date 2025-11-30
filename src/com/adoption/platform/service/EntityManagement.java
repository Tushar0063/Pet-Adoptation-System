package com.adoption.platform.service;

import com.adoption.platform.exception.EntityNotFoundException;
import java.util.List;

// Demonstrates the use of Generics (<T>)
public interface EntityManagement<T> {
    void create(T entity);
    T getById(int id) throws EntityNotFoundException;
    void update(T entity);
    void delete(int id) throws EntityNotFoundException;
    List<T> getAll();
}
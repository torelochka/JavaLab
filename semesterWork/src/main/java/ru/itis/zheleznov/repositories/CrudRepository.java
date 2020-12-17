package ru.itis.zheleznov.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    boolean save(T entity);
    boolean update(T entity);
    List<T> getAll();
    Optional<T> getById(long id);
    boolean delete(T entity);
}

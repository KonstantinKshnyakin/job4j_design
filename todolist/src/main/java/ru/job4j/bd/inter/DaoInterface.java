package ru.job4j.bd.inter;

import java.util.List;

public interface DaoInterface<T> {

    void save(T entity);

    void update(T entity);

    T findById(int id);

    void delete(T entity);

    List<T> findAll();

    void deleteAll();
}

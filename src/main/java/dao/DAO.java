package dao;

import java.util.List;

public interface DAO<E> {
    void add(E entity);
    E getByID(Integer ID);
    List<E> getAll();
    void edit(E entity);
    void remove(E entity);
}
package dao;

import java.sql.Connection;
import java.util.List;

public interface DAOConnectionPassing<E> {
    void add(E entity, Connection connection);
    E getById(long id);
    List<E> getAll();
    void edit(E entity, Connection connection);
    void remove(E entity, Connection connection);
}
package dao;

import java.sql.Connection;
import java.util.List;

public interface DAOConnectionPassing<E> {
    void add(E entity, Connection connection);
    E getById(int id, Connection connection);
    List<E> getAll(Connection connection);
    void edit(E entity, Connection connection);
    void remove(E entity, Connection connection);
}
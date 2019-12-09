package service;

import dao.DAOConnectionPassing;
import datasource.HikariCPDataSource;
import entity.Study;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudyService {
    private final DAOConnectionPassing<Study> daoConnectionPassing;
    private Connection connection;

    public StudyService(DAOConnectionPassing<Study> daoConnectionPassing) {
        this.daoConnectionPassing = daoConnectionPassing;
    }

    private void rollbackConnection(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Study entity) {
        try {
            connection = HikariCPDataSource.getConnection();
            connection.setAutoCommit(false);
            daoConnectionPassing.add(entity, connection);
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public Study getById(long id) {
        return daoConnectionPassing.getById(id);
    }

    public List<Study> getAll() {
        return daoConnectionPassing.getAll();
    }

    public void edit(Study entity) {
        try {
            connection = HikariCPDataSource.getConnection();
            connection.setAutoCommit(false);
            daoConnectionPassing.edit(entity, connection);
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void remove(Study entity) {
        try {
            connection = HikariCPDataSource.getConnection();
            connection.setAutoCommit(false);
            daoConnectionPassing.remove(entity, connection);
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
}
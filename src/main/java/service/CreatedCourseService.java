package service;

import container.annotation.Service;
import dao.DAOConnectionPassing;
import datasource.HikariCPDataSource;
import entity.CreatedCourse;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class CreatedCourseService {
    private final DAOConnectionPassing<CreatedCourse> daoConnectionPassing;
    private Connection connection;

    public CreatedCourseService(DAOConnectionPassing<CreatedCourse> daoConnectionPassing) {
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

    public void add(CreatedCourse entity) {
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

    public CreatedCourse getById(long id) {
        return daoConnectionPassing.getById(id);
    }

    public List<CreatedCourse> getAll() {
        return daoConnectionPassing.getAll();
    }

    public void edit(CreatedCourse entity) {
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

    public void remove(CreatedCourse entity) {
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
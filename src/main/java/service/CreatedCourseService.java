package service;

import dao.CreatedCourseDAO;
import dao.DAOFactory;
import datasource.HikariCPDataSource;
import entity.CreatedCourse;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CreatedCourseService {
    private CreatedCourseDAO createdCourseDAO;
    private Connection connection;

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
            createdCourseDAO = DAOFactory.getCreatedCourseDAO();
            createdCourseDAO.add(entity, connection);
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public CreatedCourse getById(int id) {
        return createdCourseDAO.getById(id);
    }

    public List<CreatedCourse> getAll() {
        return createdCourseDAO.getAll();
    }

    public void edit(CreatedCourse entity) {
        try {
            connection = HikariCPDataSource.getConnection();
            connection.setAutoCommit(false);
            createdCourseDAO = DAOFactory.getCreatedCourseDAO();
            createdCourseDAO.edit(entity, connection);
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
            createdCourseDAO = DAOFactory.getCreatedCourseDAO();
            createdCourseDAO.remove(entity, connection);
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
}
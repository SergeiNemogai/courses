package service;

import dao.DAOFactory;
import dao.UserDAO;
import datasource.HikariCPDataSource;
import entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDAO userDAO;
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

    public void add(User entity) {
        try {
            connection = HikariCPDataSource.getConnection();
            connection.setAutoCommit(false);
            userDAO = DAOFactory.getUserDAO();
            userDAO.add(entity, connection);
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public User getById(int id) {
        return userDAO.getById(id);
    }

    public List<User> getAll() {
        return userDAO.getAll();
    }

    public void edit(User entity) {
        try {
            connection = HikariCPDataSource.getConnection();
            connection.setAutoCommit(false);
            userDAO = DAOFactory.getUserDAO();
            userDAO.edit(entity, connection);
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void remove(User entity) {
        try {
            connection = HikariCPDataSource.getConnection();
            connection.setAutoCommit(false);
            userDAO = DAOFactory.getUserDAO();
            userDAO.remove(entity, connection);
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public User getCourseCreator(String courseName) {
        return userDAO.getCourseCreator(courseName);
    }

    public List<User> getStudentsByCourse(String courseName) {
        return userDAO.getStudentsByCourse(courseName);
    }

    public List<User> getStudentsByTeacherName(String name) {
        return userDAO.getStudentsByTeacherName(name);
    }
}
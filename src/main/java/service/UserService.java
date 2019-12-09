package service;

import container.annotation.Service;
import dao.UserDAO;
import datasource.HikariCPDataSource;
import entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;
    private Connection connection;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
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

    public void add(User entity) {
        try {
            connection = HikariCPDataSource.getConnection();
            connection.setAutoCommit(false);
            userDAO.add(entity, connection);
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public User getById(long id) {
        return userDAO.getById(id);
    }

    public List<User> getAll() {
        return userDAO.getAll();
    }

    public void edit(User entity) {
        try {
            connection = HikariCPDataSource.getConnection();
            connection.setAutoCommit(false);
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
package service;

import dao.CourseDAO;
import dao.DAOFactory;
import datasource.HikariCPDataSource;
import entity.Course;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CourseService {
    private CourseDAO courseDAO;
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

    public void add(Course entity) {
        try {
            connection = HikariCPDataSource.getConnection();
            connection.setAutoCommit(false);
            courseDAO = DAOFactory.getCourseDAO();
            courseDAO.add(entity, connection);
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public Course getById(int id) {
        try {
            connection = HikariCPDataSource.getConnection();
            courseDAO = DAOFactory.getCourseDAO();
            return courseDAO.getById(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public List<Course> getAll() {
        try {
            connection = HikariCPDataSource.getConnection();
            courseDAO = DAOFactory.getCourseDAO();
            return courseDAO.getAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public void edit(Course entity) {
        try {
            connection = HikariCPDataSource.getConnection();
            connection.setAutoCommit(false);
            courseDAO = DAOFactory.getCourseDAO();
            courseDAO.edit(entity, connection);
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void remove(Course entity) {
        try {
            connection = HikariCPDataSource.getConnection();
            connection.setAutoCommit(false);
            courseDAO = DAOFactory.getCourseDAO();
            courseDAO.remove(entity, connection);
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public List<Course> getCoursesOnStudy() {
        try {
            connection = HikariCPDataSource.getConnection();
            courseDAO = DAOFactory.getCourseDAO();
            return courseDAO.getCoursesOnStudy(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return null;
    }
}
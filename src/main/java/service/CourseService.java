package service;

import container.annotation.Service;
import dao.CourseDAO;
import datasource.HikariCPDataSource;
import entity.Course;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class CourseService {
    private final CourseDAO courseDAO;
    private Connection connection;

    public CourseService(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
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

    public void add(Course entity) {
        try {
            connection = HikariCPDataSource.getConnection();
            connection.setAutoCommit(false);
            courseDAO.add(entity, connection);
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public Course getById(long id) {
        return courseDAO.getById(id);
    }

    public List<Course> getAll() {
        return courseDAO.getAll();
    }

    public void edit(Course entity) {
        try {
            connection = HikariCPDataSource.getConnection();
            connection.setAutoCommit(false);
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
        return courseDAO.getCoursesOnStudy();
    }
}
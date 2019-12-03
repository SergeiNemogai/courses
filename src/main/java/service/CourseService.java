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

    public void add(Course entity) {
        try (Connection connection = HikariCPDataSource.getConnection()) {
            courseDAO = DAOFactory.getCourseDAO();
            connection.setAutoCommit(false);
            courseDAO.add(entity, connection);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Course getById(int id) {
        try (Connection connection = HikariCPDataSource.getConnection()) {
            courseDAO = DAOFactory.getCourseDAO();
            return courseDAO.getById(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Course> getAll() {
        try (Connection connection = HikariCPDataSource.getConnection()) {
            courseDAO = DAOFactory.getCourseDAO();
            return courseDAO.getAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void edit(Course entity) {
        try (Connection connection = HikariCPDataSource.getConnection()) {
            courseDAO = DAOFactory.getCourseDAO();
            connection.setAutoCommit(false);
            courseDAO.edit(entity, connection);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Course entity) {
        try (Connection connection = HikariCPDataSource.getConnection()) {
            courseDAO = DAOFactory.getCourseDAO();
            connection.setAutoCommit(false);
            courseDAO.remove(entity, connection);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Course> getCoursesOnStudy() {
        try (Connection connection = HikariCPDataSource.getConnection()) {
            courseDAO = DAOFactory.getCourseDAO();
            return courseDAO.getCoursesOnStudy(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
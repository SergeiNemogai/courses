package dao;

import datasource.HikariCPDataSource;
import entity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO implements DAOConnectionPassing<Course> {
    @Override
    public void add(Course entity, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into courses.courses values(?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setDate(3, entity.getCreatedAt());
            preparedStatement.setTimestamp(4, entity.getStartDateTime());
            preparedStatement.setTimestamp(5, entity.getEndDateTime());
            preparedStatement.setString(6, entity.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Course getById(long id) {
        Course course = null;
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from courses.courses where id=?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    course = Course.builder()
                            .id(resultSet.getLong(1))
                            .name(resultSet.getString(2))
                            .createdAt(resultSet.getDate(3))
                            .startDateTime(resultSet.getTimestamp(4))
                            .endDateTime(resultSet.getTimestamp(5))
                            .status(resultSet.getString(6))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from courses.courses");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                courses.add(Course.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .createdAt(resultSet.getDate(3))
                        .startDateTime(resultSet.getTimestamp(4))
                        .endDateTime(resultSet.getTimestamp(5))
                        .status(resultSet.getString(6))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public void edit(Course entity, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                     "update courses.courses " +
                             "set name=?, created_at=? ,start_datetime=?, end_datetime=?, status=? " +
                             "where id=?")) {
            preparedStatement.setLong(6, entity.getId());
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDate(2, entity.getCreatedAt());
            preparedStatement.setTimestamp(3, entity.getStartDateTime());
            preparedStatement.setTimestamp(4, entity.getEndDateTime());
            preparedStatement.setString(5, entity.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Course entity, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                     "delete from courses.courses where id=?")) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Course> getCoursesOnStudy() {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from courses.courses where status=?")) {
            preparedStatement.setString(1,"on study");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    courses.add(Course.builder()
                            .id(resultSet.getLong(1))
                            .name(resultSet.getString(2))
                            .createdAt(resultSet.getDate(3))
                            .startDateTime(resultSet.getTimestamp(4))
                            .endDateTime(resultSet.getTimestamp(5))
                            .status(resultSet.getString(6))
                            .build());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}
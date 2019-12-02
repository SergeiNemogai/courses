package dao;

import datasource.HikariCPDataSource;
import entity.CreatedCourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreatedCourseDAO implements DAO<CreatedCourse> {
    @Override
    public void add(CreatedCourse entity) {
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into courses.created_courses values(?, ?)")) {
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getCourseId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CreatedCourse getById(int id) {
        CreatedCourse createdCourse = null;
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from courses.created_courses where user_id=?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    createdCourse = CreatedCourse.builder()
                            .userId(resultSet.getInt(1))
                            .courseId(resultSet.getInt(2))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return createdCourse;
    }

    @Override
    public List<CreatedCourse> getAll() {
        List<CreatedCourse> createdCourses = new ArrayList<>();
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from courses.created_courses")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    createdCourses.add(CreatedCourse.builder()
                            .userId(resultSet.getInt(1))
                            .courseId(resultSet.getInt(2))
                            .build());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return createdCourses;
    }

    @Override
    public void edit(CreatedCourse entity) {
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "update courses.created_courses set user_id=?, course_id=? where user_id=? and course_id=?")) {
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getCourseId());
            preparedStatement.setInt(3, entity.getUserId());
            preparedStatement.setInt(4, entity.getCourseId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(CreatedCourse entity) {
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "delete from courses.created_courses where user_id=? and course_id=?")) {
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getCourseId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
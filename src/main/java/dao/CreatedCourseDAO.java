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
            preparedStatement.setInt(1, entity.getUserID());
            preparedStatement.setInt(2, entity.getCourseID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CreatedCourse getByID(int ID) {
        CreatedCourse createdCourse = null;
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from courses.created_courses where user_id=?")) {
            preparedStatement.setInt(1, ID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    createdCourse = new CreatedCourse(resultSet.getInt(1),
                            resultSet.getInt(2));
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
                    createdCourses.add(new CreatedCourse(resultSet.getInt(1),
                            resultSet.getInt(2)));
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
            preparedStatement.setInt(1, entity.getUserID());
            preparedStatement.setInt(2, entity.getCourseID());
            preparedStatement.setInt(3, entity.getUserID());
            preparedStatement.setInt(4, entity.getCourseID());
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
            preparedStatement.setInt(1, entity.getUserID());
            preparedStatement.setInt(2, entity.getCourseID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
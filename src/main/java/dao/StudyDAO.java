package dao;

import datasource.HikariCPDataSource;
import entity.Study;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudyDAO implements DAO<Study> {
    @Override
    public void add(Study entity) {
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into courses.study values(?, ?, ?)")) {
            preparedStatement.setInt(1, entity.getID());
            preparedStatement.setInt(2, entity.getCourseID());
            preparedStatement.setInt(3, entity.getUserID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Study getByID(int ID) {
        Study study = null;
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from courses.study where id=?")) {
            preparedStatement.setInt(1, ID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    study = new Study(resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getInt(3));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return study;
    }

    @Override
    public List<Study> getAll() {
        List<Study> studies = new ArrayList<>();
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from courses.study")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    studies.add(new Study(resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getInt(3)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studies;
    }

    @Override
    public void edit(Study entity) {
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "update courses.study set user_id=?, course_id=? where id=?")) {
            preparedStatement.setInt(1, entity.getUserID());
            preparedStatement.setInt(2, entity.getCourseID());
            preparedStatement.setInt(3, entity.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Study entity) {
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "delete from courses.study where id=?")) {
            preparedStatement.setInt(1, entity.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
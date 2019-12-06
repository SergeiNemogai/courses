package dao;

import container.annotation.Component;
import datasource.HikariCPDataSource;
import entity.Study;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudyDAO implements DAOConnectionPassing<Study> {
    @Override
    public void add(Study entity, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into courses.study values(?, ?, ?)")) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setLong(2, entity.getCourseId());
            preparedStatement.setLong(3, entity.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Study getById(long id) {
        Study study = null;
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from courses.study where id=?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    study = Study.builder()
                            .id(resultSet.getLong(1))
                            .courseId(resultSet.getLong(2))
                            .userId(resultSet.getLong(3))
                            .build();
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
                    studies.add(Study.builder()
                            .id(resultSet.getLong(1))
                            .courseId(resultSet.getLong(2))
                            .userId(resultSet.getLong(3))
                            .build());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studies;
    }

    @Override
    public void edit(Study entity, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                     "update courses.study set user_id=?, course_id=? where id=?")) {
            preparedStatement.setLong(1, entity.getUserId());
            preparedStatement.setLong(2, entity.getCourseId());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Study entity, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                     "delete from courses.study where id=?")) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
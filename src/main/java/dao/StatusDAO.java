package dao;

import datasource.HikariCPDataSource;
import entity.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusDAO implements DAO<Status> {
    @Override
    public void add(Status entity) {
        try {
            Connection connection = HikariCPDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into courses.statuses value ?");
            preparedStatement.setString(1, entity.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Status getByID(int ID) {
        return null;
    }

    @Override
    public List<Status> getAll() {
        List<Status> statuses = new ArrayList<>();
        try {
             Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from courses.statuses");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                statuses.add(new Status(resultSet.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statuses;
    }

    @Override
    public void edit(Status entity) {
        try {
            Connection connection = HikariCPDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update courses.statuses set status=? where status=?");
            preparedStatement.setString(1, entity.getStatus());
            preparedStatement.setString(2, entity.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Status entity) {
        try {
            Connection connection = HikariCPDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from courses.statuses where status=?");
            preparedStatement.setString(1, entity.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
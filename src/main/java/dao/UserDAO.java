package dao;

import datasource.HikariCPDataSource;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User> {
    @Override
    public void add(User entity) {
        try {
            Connection connection = HikariCPDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into courses.users values(?, ?, ?, ?)");
            preparedStatement.setInt(1, entity.getID());
            preparedStatement.setString(2, entity.getFirstName());
            preparedStatement.setString(3, entity.getLastName());
            preparedStatement.setString(4, entity.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getByID(int ID) {
        User user = null;
        try {
            Connection connection = HikariCPDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from courses.users where id=?");
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> usersList = new ArrayList<>();
        try {
            Connection connection = HikariCPDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from courses.users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usersList.add(new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public void edit(User entity) {
        try {
            Connection connection = HikariCPDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update courses.users set fname=?, lname=?, role=? where id=?");
            preparedStatement.setInt(4, entity.getID());
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(User entity) {
        try {
            Connection connection = HikariCPDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from courses.users where id=?");
            preparedStatement.setInt(1, entity.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
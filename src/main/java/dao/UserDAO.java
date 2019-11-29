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
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from courses.users where id=?");
            preparedStatement.setInt(1, entity.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getCourseCreator(String courseName) {
        User user = null;
        try {
            Connection connection = HikariCPDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select u.id, u.fname, u.lname, u.role " +
                            "from courses.users u " +
                            "inner join courses.created_courses cc on u.id = cc.user_id " +
                            "inner join courses.courses c on cc.course_id = c.id " +
                            "where c.name=?");
            preparedStatement.setString(1, courseName);
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

    public List<User> getStudentsByCourse(String courseName) {
        List<User> users = new ArrayList<>();
        User user;
        try {
            Connection connection = HikariCPDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select u.id, u.fname, u.lname, u.role " +
                            "from courses.users u " +
                            "inner join courses.study s on u.id = s.user_id " +
                            "inner join courses.courses c on s.course_id = c.id " +
                            "where c.name=?");
            preparedStatement.setString(1, courseName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> getStudentsByTeacherName(String name) {
        List<User> users = new ArrayList<>();
        User user;
        try {
            Connection connection = HikariCPDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select distinct u.id, u.fname, u.lname, u.role " +
                            "from courses.users u " +
                            "inner join courses.study s on u.id = s.user_id " +
                            "inner join courses.courses c on s.course_id = c.id " +
                            "inner join courses.created_courses cr on c.id = cr.course_id " +
                            "inner join courses.users u1 on cr.user_id=u1.id " +
                            "where c.status='studied' and u1.lname=?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
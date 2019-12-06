package dao;

import container.annotation.Component;
import datasource.HikariCPDataSource;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO implements DAOConnectionPassing<User> {
    @Override
    public void add(User entity, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into courses.users values(?, ?, ?, ?)")) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getFirstName());
            preparedStatement.setString(3, entity.getLastName());
            preparedStatement.setString(4, entity.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getById(long id) {
        User user = null;
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from courses.users where id=?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = User.builder()
                            .id(resultSet.getLong(1))
                            .firstName(resultSet.getString(2))
                            .lastName(resultSet.getString(3))
                            .role(resultSet.getString(4))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> usersList = new ArrayList<>();
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from courses.users")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    usersList.add(User.builder()
                            .id(resultSet.getLong(1))
                            .firstName(resultSet.getString(2))
                            .lastName(resultSet.getString(3))
                            .role(resultSet.getString(4))
                            .build());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public void edit(User entity, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                     "update courses.users set fname=?, lname=?, role=? where id=?")) {
            preparedStatement.setLong(4, entity.getId());
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(User entity, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                     "delete from courses.users where id=?")) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getCourseCreator(String courseName) {
        User user = null;
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select u.id, u.fname, u.lname, u.role " +
                             "from courses.users u " +
                             "inner join courses.created_courses cc on u.id = cc.user_id " +
                             "inner join courses.courses c on cc.course_id = c.id " +
                             "where c.name=?")) {
            preparedStatement.setString(1, courseName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = User.builder()
                            .id(resultSet.getLong(1))
                            .firstName(resultSet.getString(2))
                            .lastName(resultSet.getString(3))
                            .role(resultSet.getString(4))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getStudentsByCourse(String courseName) {
        List<User> users = new ArrayList<>();
        User user;
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select u.id, u.fname, u.lname, u.role " +
                             "from courses.users u " +
                             "inner join courses.study s on u.id = s.user_id " +
                             "inner join courses.courses c on s.course_id = c.id " +
                             "where c.name=?")) {
            prepareStatement(courseName, users, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> getStudentsByTeacherName(String name) {
        List<User> users = new ArrayList<>();
        User user;
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select distinct u.id, u.fname, u.lname, u.role " +
                             "from courses.users u " +
                             "inner join courses.study s on u.id = s.user_id " +
                             "inner join courses.courses c on s.course_id = c.id " +
                             "inner join courses.created_courses cr on c.id = cr.course_id " +
                             "inner join courses.users u1 on cr.user_id=u1.id " +
                             "where c.status='studied' and u1.lname=?")) {
            prepareStatement(name, users, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private void prepareStatement(String name, List<User> users, PreparedStatement preparedStatement) throws SQLException {
        User user;
        preparedStatement.setString(1, name);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getLong(1))
                        .firstName(resultSet.getString(2))
                        .lastName(resultSet.getString(3))
                        .role(resultSet.getString(4))
                        .build();
                users.add(user);
            }
        }
    }
}
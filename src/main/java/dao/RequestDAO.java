package dao;

import datasource.HikariCPDataSource;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {
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
}
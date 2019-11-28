package dao;

import datasource.HikariCPDataSource;
import entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsersDAO implements DAO<Users> {

    @Override
    public void add(Users entity) {
        // nothing to add
    }

    @Override
    public Users getByID(Integer ID) {
        return null;
    }

    @Override
    public List<Users> getAll() {
        try {
            Connection connection = HikariCPDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from courses.users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.printf("%d %s %s %s \n", resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void edit(Users entity) {
        // nothing to edit
    }

    @Override
    public void remove(Users entity) {
        // nothing to edit
    }
}

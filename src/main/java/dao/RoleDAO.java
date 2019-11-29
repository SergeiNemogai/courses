package dao;

import datasource.HikariCPDataSource;
import entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO implements DAO<Role> {
    @Override
    public void add(Role entity) {
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into courses.roles value ?")) {
            preparedStatement.setString(1, entity.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Role getByID(int ID) {
        return null;
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from courses.roles")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    roles.add(new Role(resultSet.getString(1)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public void edit(Role entity) {
        // no sense to change role
    }

    @Override
    public void remove(Role entity) {
        // no sense to remove role
    }
}
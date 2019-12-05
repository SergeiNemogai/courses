package service;

import dao.DAOFactory;
import dao.StudyDAO;
import datasource.HikariCPDataSource;
import entity.Study;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudyService {
    private final StudyDAO studyDAO = DAOFactory.getStudyDAO();
    private Connection connection;

    private void rollbackConnection(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Study entity) {
        try {
            connection = HikariCPDataSource.getConnection();
            connection.setAutoCommit(false);
            studyDAO.add(entity, connection);
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public Study getById(long id) {
        return studyDAO.getById(id);
    }

    public List<Study> getAll() {
        return studyDAO.getAll();
    }

    public void edit(Study entity) {
        try {
            connection = HikariCPDataSource.getConnection();
            connection.setAutoCommit(false);
            studyDAO.edit(entity, connection);
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void remove(Study entity) {
        try {
            connection = HikariCPDataSource.getConnection();
            connection.setAutoCommit(false);
            studyDAO.remove(entity, connection);
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
}
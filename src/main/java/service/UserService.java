package service;

import dao.DAOFactory;
import dao.UserDAO;
import entity.User;

import java.util.List;

public class UserService {
    private UserDAO userDAO = DAOFactory.getUserDAO();

    public void add(User entity) {
        userDAO.add(entity);
    }

    public User getById(int id) {
        return userDAO.getById(id);
    }

    public List<User> getAll() {
        return userDAO.getAll();
    }

    public void edit(User entity) {
        userDAO.edit(entity);
    }

    public void remove(User entity) {
        userDAO.remove(entity);
    }

    public User getCourseCreator(String courseName) {
        return userDAO.getCourseCreator(courseName);
    }

    public List<User> getStudentsByCourse(String courseName) {
        return userDAO.getStudentsByCourse(courseName);
    }

    public List<User> getStudentsByTeacherName(String name) {
        return userDAO.getStudentsByTeacherName(name);
    }
}
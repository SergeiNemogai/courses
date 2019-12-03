package service;

import dao.CreatedCourseDAO;
import dao.DAOFactory;
import entity.CreatedCourse;

import java.util.List;

public class CreatedCourseService {
    private CreatedCourseDAO createdCourseDAO = DAOFactory.getCreatedCourseDAO();

    public void add(CreatedCourse entity) {
        createdCourseDAO.add(entity);
    }

    public CreatedCourse getById(int id) {
        return createdCourseDAO.getById(id);
    }

    public List<CreatedCourse> getAll() {
        return createdCourseDAO.getAll();
    }

    public void edit(CreatedCourse entity) {
        createdCourseDAO.edit(entity);
    }

    public void remove(CreatedCourse entity) {
        createdCourseDAO.remove(entity);
    }
}
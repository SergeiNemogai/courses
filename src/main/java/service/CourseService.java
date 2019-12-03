package service;

import dao.CourseDAO;
import dao.DAOFactory;
import entity.Course;

import java.util.List;

public class CourseService {
    private CourseDAO courseDAO = DAOFactory.getCourseDAO();

    public void add(Course entity) {
        courseDAO.add(entity);
    }

    public Course getById(int id) {
        return courseDAO.getById(id);
    }

    public List<Course> getAll() {
        return courseDAO.getAll();
    }

    public void edit(Course entity) {
        courseDAO.edit(entity);
    }

    public void remove(Course entity) {
        courseDAO.remove(entity);
    }

    public List<Course> getCoursesOnStudy() {
        return courseDAO.getCoursesOnStudy();
    }
}
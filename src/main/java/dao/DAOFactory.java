package dao;

import entity.Study;

public class DAOFactory {
    private static CourseDAO courseDAO;
    private static CreatedCourseDAO createdCourseDAO;
    private static RoleDAO roleDAO;
    private static StatusDAO statusDAO;
    private static StudyDAO studyDAO;
    private static UserDAO userDAO;

    public static CourseDAO getCourseDAO() {
        if(courseDAO == null) {
            courseDAO = new CourseDAO();
        }
        return courseDAO;
    }

    public static CreatedCourseDAO getCreatedCourseDAO() {
        if (createdCourseDAO == null) {
            createdCourseDAO = new CreatedCourseDAO();
        }
        return createdCourseDAO;
    }

    public static RoleDAO getRoleDAO() {
        if (roleDAO == null) {
            roleDAO = new RoleDAO();
        }
        return roleDAO;
    }

    public static StatusDAO getStatusDAO() {
        if (statusDAO == null) {
            statusDAO = new StatusDAO();
        }
        return statusDAO;
    }

    public static StudyDAO getStudyDAO() {
        if (studyDAO == null) {
            studyDAO = new StudyDAO();
        }
        return studyDAO;
    }

    public static UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    private DAOFactory() {
        // empty body
    }
}
package dao;

public class DAOFactory {
    private static CourseDAO courseDAO;
    private static CreatedCourseDAO createdCourseDAO;
    private static RoleDAO roleDAO;
    private static StatusDAO statusDAO;
    private static StudyDAO studyDAO;
    private static UserDAO userDAO;

    public static CourseDAO getCourseDAO() {
        if(courseDAO == null) {
            return new CourseDAO();
        }
        return courseDAO;
    }

    public static CreatedCourseDAO getCreatedCourseDAO() {
        if (createdCourseDAO == null) {
            return new CreatedCourseDAO();
        }
        return createdCourseDAO;
    }

    public static RoleDAO getRoleDAO() {
        if (roleDAO == null) {
            return new RoleDAO();
        }
        return roleDAO;
    }

    public static StatusDAO getStatusDAO() {
        if (statusDAO == null) {
            return new StatusDAO();
        }
        return statusDAO;
    }

    public static StudyDAO getStudyDAO() {
        if (studyDAO == null) {
            return new StudyDAO();
        }
        return studyDAO;
    }

    public static UserDAO getUserDAO() {
        if (userDAO == null) {
            return new UserDAO();
        }
        return userDAO;
    }

    private DAOFactory() {
        // empty body
    }
}
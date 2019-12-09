package service;

import dao.CourseDAO;
import dao.CreatedCourseDAO;
import dao.StudyDAO;
import dao.UserDAO;

public class ServiceFactory {
    private static CourseService courseService;
    private static CreatedCourseService createdCourseService;
    private static StudyService studyService;
    private static UserService userService;

    public static CourseService getCourseService() {
        if (courseService == null) {
            courseService = new CourseService(new CourseDAO());
        }
        return courseService;
    }

    public static CreatedCourseService getCreatedCourseService() {
        if (createdCourseService == null) {
            createdCourseService = new CreatedCourseService(new CreatedCourseDAO());
        }
        return createdCourseService;
    }

    public static StudyService getStudyService() {
        if (studyService == null) {
            studyService = new StudyService(new StudyDAO());
        }
        return studyService;
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService(new UserDAO());
        }
        return userService;
    }

    private ServiceFactory() {
        // private constructor
    }
}
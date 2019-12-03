package service;

public class ServiceFactory {
    private static CourseService courseService;
    private static CreatedCourseService createdCourseService;
    private static StudyService studyService;
    private static UserService userService;

    public static CourseService getCourseService() {
        if (courseService == null) {
            courseService = new CourseService();
        }
        return courseService;
    }

    public static CreatedCourseService getCreatedCourseService() {
        if (createdCourseService == null) {
            createdCourseService = new CreatedCourseService();
        }
        return createdCourseService;
    }

    public static StudyService getStudyService() {
        if (studyService == null) {
            studyService = new StudyService();
        }
        return studyService;
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    private ServiceFactory() {
        // private constructor
    }
}
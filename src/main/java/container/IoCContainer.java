package container;

import controller.CourseController;
import controller.CreatedCourseController;
import controller.StudyController;
import controller.UserController;
import dao.CourseDAO;
import dao.CreatedCourseDAO;
import dao.StudyDAO;
import dao.UserDAO;
import service.CourseService;
import service.CreatedCourseService;
import service.StudyService;
import service.UserService;

import java.util.HashMap;
import java.util.Map;

public class IoCContainer {
    private Map<String, Object> container = new HashMap<>();

    public Object getBean(String beanName) {
        return container.get(beanName);
    }

    public IoCContainer() {
        instantiate();
    }

    private void instantiate() {
        container.put("courses.DAO", new CourseDAO());
        container.put("created-courses.DAO", new CreatedCourseDAO());
        container.put("studies.DAO", new StudyDAO());
        container.put("users.DAO", new UserDAO());

        container.put("courses.Service", new CourseService(
                (CourseDAO) getBean("courses.DAO")));
        container.put("created-courses.Service", new CreatedCourseService(
                (CreatedCourseDAO) getBean("created-courses.DAO")));
        container.put("studies.Service", new StudyService(
                (StudyDAO) getBean("studies.DAO")));
        container.put("users.Service", new UserService(
                (UserDAO) getBean("users.DAO")));

        container.put("courses", new CourseController(
                (CourseService) getBean("courses.Service")));
        container.put("created-courses", new CreatedCourseController(
                (CreatedCourseService) getBean("created-courses.Service")));
        container.put("studies", new StudyController(
                (StudyService) getBean("studies.Service")));
        container.put("users", new UserController(
                (UserService) getBean("users.Service")));
    }
}
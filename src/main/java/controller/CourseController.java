package controller;

import entity.Course;
import service.CourseService;
import service.ServiceFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CourseController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*Container container = new Container("service");
        CourseService courseService = (CourseService) container.getBean("CourseService");*/
        resp.setContentType("text/html;charset=UTF-8");
        CourseService courseService = ServiceFactory.getCourseService();
        String id = req.getParameter("id");
        boolean onStudy = Boolean.parseBoolean(req.getParameter("on-study"));
        try (PrintWriter writer = resp.getWriter()) {
            if (id == null) {
                List<Course> courses = new ArrayList<>();
                if (onStudy) {
                    courses.addAll(courseService.getCoursesOnStudy());
                } else {
                    courses.addAll(courseService.getAll());
                }
                for(Course course : courses) {
                    writer.println("<p>" + course + "<p>");
                }
            } else {
                writer.println(courseService.getById(Long.parseLong(id)));
            }
        }
    }
}
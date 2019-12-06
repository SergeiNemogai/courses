package controller;

import container.Container;
import entity.Course;
import service.CourseService;
import service.ServiceFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CourseController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*Container container = new Container("service");
        CourseService courseService = (CourseService) container.getBean("CourseService");*/
        resp.setContentType("text/html;charset=UTF-8");
        CourseService courseService = ServiceFactory.getCourseService();

        try (PrintWriter writer = resp.getWriter()) {
            for(Course course : courseService.getAll()) {
                writer.println("<p>" + course + "<p>");
            }
        }
    }
}
package controller;

import entity.Course;
import service.CourseService;
import service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CourseController extends HttpServlet {
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        courseService = ServiceFactory.getCourseService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*Container container = new Container("service");
        CourseService courseService = (CourseService) container.getBean("CourseService");*/
        resp.setContentType("text/html;charset=UTF-8");
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        Date createdAt = Date.valueOf(req.getParameter("created"));
        Timestamp start = Timestamp.valueOf(req.getParameter("start"));
        Timestamp end = Timestamp.valueOf(req.getParameter("start"));
        String status = req.getParameter("status");
        if (name != null && createdAt != null && status != null) {
            courseService.add(Course.builder()
                    .id(id)
                    .name(name)
                    .createdAt(createdAt)
                    .startDateTime(start)
                    .endDateTime(end)
                    .status(status)
                    .build());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        Date createdAt = Date.valueOf(req.getParameter("created"));
        Timestamp start = Timestamp.valueOf(req.getParameter("start"));
        Timestamp end = Timestamp.valueOf(req.getParameter("start"));
        String status = req.getParameter("status");
        if (name != null && createdAt != null && status != null) {
            courseService.edit(Course.builder()
                    .id(id)
                    .name(name)
                    .createdAt(createdAt)
                    .startDateTime(start)
                    .endDateTime(end)
                    .status(status)
                    .build());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        Date createdAt = Date.valueOf(req.getParameter("created"));
        Timestamp start = Timestamp.valueOf(req.getParameter("start"));
        Timestamp end = Timestamp.valueOf(req.getParameter("start"));
        String status = req.getParameter("status");
        if (name != null && createdAt != null && status != null) {
            courseService.remove(Course.builder()
                    .id(id)
                    .name(name)
                    .createdAt(createdAt)
                    .startDateTime(start)
                    .endDateTime(end)
                    .status(status)
                    .build());
        }
    }
}
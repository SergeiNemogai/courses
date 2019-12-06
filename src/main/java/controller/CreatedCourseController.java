package controller;

import entity.CreatedCourse;
import service.CreatedCourseService;
import service.ServiceFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CreatedCourseController extends HttpServlet {
    //TODO: all CRUD operations from CreatedCourseService

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        CreatedCourseService createdCourseService = ServiceFactory.getCreatedCourseService();
        String id = req.getParameter("id");
        try (PrintWriter writer = resp.getWriter()) {
            if (id == null) {
                for(CreatedCourse createdCourse : createdCourseService.getAll()) {
                    writer.println("<p>" + createdCourse + "<p>");
                }
            } else {
                writer.println(createdCourseService.getById(Long.parseLong(id)));
            }
        }
    }
}
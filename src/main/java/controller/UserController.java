package controller;

import entity.User;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UserController extends HttpServlet {
    //TODO: all CRUD operations from UserService

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        UserService userService = ServiceFactory.getUserService();
        String id = req.getParameter("id");
        String courseName = req.getParameter("course-name");
        boolean isStudents = Boolean.parseBoolean(req.getParameter("student"));
        String teacherName = req.getParameter("teacher-name");
        try (PrintWriter writer = resp.getWriter()) {
            if (id == null) {
                List<User> users = new ArrayList<>();
                if (isStudents) {
                    if (teacherName == null && courseName != null) {
                        users.addAll(userService.getStudentsByCourse(courseName));
                    } else if(teacherName != null) {
                        users.addAll(userService.getStudentsByTeacherName(teacherName));
                    }
                } else if (teacherName == null && courseName != null) {
                    writer.println(userService.getCourseCreator(courseName));
                } else {
                    users.addAll(userService.getAll());
                }
                if (!users.isEmpty()) {
                    for (User user : users) {
                        writer.println("<p>" + user + "</p>");
                    }
                }
            } else {
                writer.println(userService.getById(Long.parseLong(id)));
            }
        }
    }
}
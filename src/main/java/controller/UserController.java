package controller;

import entity.User;
import service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UserController extends HttpServlet {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("id"));
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String role = req.getParameter("role");
        if (fname != null && lname != null && role != null) {
            userService.add(User.builder()
                    .id(id)
                    .firstName(fname)
                    .lastName(lname)
                    .role(role)
                    .build());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("id"));
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String role = req.getParameter("role");
        if (fname != null && lname != null && role != null) {
            userService.edit(User.builder()
                    .id(id)
                    .firstName(fname)
                    .lastName(lname)
                    .role(role)
                    .build());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("id"));
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String role = req.getParameter("role");
        if (fname != null && lname != null && role != null) {
            userService.remove(User.builder()
                    .id(id)
                    .firstName(fname)
                    .lastName(lname)
                    .role(role).build());
        }
    }
}
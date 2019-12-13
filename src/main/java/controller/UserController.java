package controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.User;
import service.UserService;
import util.JsonConverter;
import util.RequestBodyParser;

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
        resp.setContentType("application/json;charset=UTF-8");
        String id = req.getParameter("id");
        String courseName = req.getParameter("course-name");
        boolean isStudents = Boolean.parseBoolean(req.getParameter("student"));
        String teacherName = req.getParameter("teacher-name");
        try (PrintWriter writer = resp.getWriter()) {
            List<User> users = new ArrayList<>();
            if (id == null) {
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
            } else {
                try {
                    users.add(userService.getById(Long.parseLong(id)));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    resp.setStatus(422);
                }
            }
            writer.println(new JsonConverter().convertToJson(users, "users"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        JsonObject jsonObject = new JsonParser().parse(RequestBodyParser.parse(req)).getAsJsonObject();
        try {
            Long id = jsonObject.get("id").getAsLong();
            String fname = jsonObject.get("fname").getAsString();
            String lname = jsonObject.get("lname").getAsString();
            String role = jsonObject.get("role").getAsString();
            if (fname != null && lname != null && role != null) {
                userService.add(User.builder()
                        .id(id)
                        .firstName(fname)
                        .lastName(lname)
                        .role(role)
                        .build());
            }
            resp.setStatus(201);
        } catch (NullPointerException e) {
            e.printStackTrace();
            resp.setStatus(422);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        JsonObject jsonObject = new JsonParser().parse(RequestBodyParser.parse(req)).getAsJsonObject();
        try {
            Long id = jsonObject.get("id").getAsLong();
            String fname = jsonObject.get("fname").getAsString();
            String lname = jsonObject.get("lname").getAsString();
            String role = jsonObject.get("role").getAsString();
            if (fname != null && lname != null && role != null) {
                userService.edit(User.builder()
                        .id(id)
                        .firstName(fname)
                        .lastName(lname)
                        .role(role)
                        .build());
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            resp.setStatus(422);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        JsonObject jsonObject = new JsonParser().parse(RequestBodyParser.parse(req)).getAsJsonObject();
        try {
            Long id = jsonObject.get("id").getAsLong();
            String fname = jsonObject.get("fname").getAsString();
            String lname = jsonObject.get("lname").getAsString();
            String role = jsonObject.get("role").getAsString();
            if (fname != null && lname != null && role != null) {
                userService.remove(User.builder()
                        .id(id)
                        .firstName(fname)
                        .lastName(lname)
                        .role(role).build());
            }
            resp.setStatus(204);
        } catch (NullPointerException e) {
            e.printStackTrace();
            resp.setStatus(422);
        }
    }
}
package controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Course;
import service.CourseService;
import util.JsonConverter;
import util.RequestBodyParser;

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
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String id = req.getParameter("id");
        boolean onStudy = Boolean.parseBoolean(req.getParameter("on-study"));
        try (PrintWriter writer = resp.getWriter()) {
            List<Course> courses = new ArrayList<>();
            if (id == null) {
                if (onStudy) {
                    courses.addAll(courseService.getCoursesOnStudy());
                } else {
                    courses.addAll(courseService.getAll());
                }
            } else {
                courses.add(courseService.getById(Long.parseLong(id)));
            }
            writer.println(new JsonConverter().convertToJson(courses, "courses"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        JsonObject jsonObject = new JsonParser().parse(RequestBodyParser.parse(req)).getAsJsonObject();
        Long id = jsonObject.get("id").getAsLong();
        String name = jsonObject.get("name").getAsString();
        Date createdAt = Date.valueOf(jsonObject.get("createdAt").getAsString());
        Timestamp start = Timestamp.valueOf(jsonObject.get("startDateTime").getAsString());
        Timestamp end = Timestamp.valueOf(jsonObject.get("endDateTime").getAsString());
        String status = jsonObject.get("status").getAsString();
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        JsonObject jsonObject = new JsonParser().parse(RequestBodyParser.parse(req)).getAsJsonObject();
        Long id = jsonObject.get("id").getAsLong();
        String name = jsonObject.get("name").getAsString();
        Date createdAt = Date.valueOf(jsonObject.get("createdAt").getAsString());
        Timestamp start = Timestamp.valueOf(jsonObject.get("startDateTime").getAsString());
        Timestamp end = Timestamp.valueOf(jsonObject.get("endDateTime").getAsString());
        String status = jsonObject.get("status").getAsString();
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        JsonObject jsonObject = new JsonParser().parse(RequestBodyParser.parse(req)).getAsJsonObject();
        Long id = jsonObject.get("id").getAsLong();
        String name = jsonObject.get("name").getAsString();
        Date createdAt = Date.valueOf(jsonObject.get("createdAt").getAsString());
        Timestamp start = Timestamp.valueOf(jsonObject.get("startDateTime").getAsString());
        Timestamp end = Timestamp.valueOf(jsonObject.get("endDateTime").getAsString());
        String status = jsonObject.get("status").getAsString();
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
package controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.CreatedCourse;
import service.CreatedCourseService;
import util.JsonConverter;
import util.JsonResponseBody;
import util.RequestBodyParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CreatedCourseController extends HttpServlet {
    private final CreatedCourseService createdCourseService;

    public CreatedCourseController(CreatedCourseService createdCourseService) {
        this.createdCourseService = createdCourseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String id = req.getParameter("id");
        try (PrintWriter writer = resp.getWriter()) {
            List<CreatedCourse> createdCourses = new ArrayList<>();
            if (id == null) {
                createdCourses.addAll(createdCourseService.getAll());
            } else {
                try {
                    createdCourses.add(createdCourseService.getById(Long.parseLong(id)));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    JsonResponseBody.sendResponseBody(resp,
                            422,
                            "error",
                            "CreatedCourse id must be numeric");
                    return;
                }
            }
            writer.println(new JsonConverter().convertToJson(createdCourses, "created_courses"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        JsonObject jsonObject = new JsonParser().parse(RequestBodyParser.parse(req)).getAsJsonObject();
        try {
            Long userId = jsonObject.get("userId").getAsLong();
            Long courseId = jsonObject.get("courseId").getAsLong();
            createdCourseService.add(CreatedCourse.builder()
                    .userId(userId)
                    .courseId(courseId)
                    .build());
            JsonResponseBody.sendResponseBody(resp,
                    201,
                    "status",
                    "CreatedCourse successfully added");
        } catch (NullPointerException e) {
            e.printStackTrace();
            JsonResponseBody.sendResponseBody(resp,
                    422,
                    "error",
                    "Invalid parameter name or value");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        JsonObject jsonObject = new JsonParser().parse(RequestBodyParser.parse(req)).getAsJsonObject();
        try {
            Long userId = jsonObject.get("userId").getAsLong();
            Long courseId = jsonObject.get("courseId").getAsLong();
            createdCourseService.edit(CreatedCourse.builder()
                    .userId(userId)
                    .courseId(courseId)
                    .build());
            JsonResponseBody.sendResponseBody(resp,
                    200,
                    "status",
                    "CreatedCourse successfully updated");
        } catch (NullPointerException e) {
            e.printStackTrace();
            JsonResponseBody.sendResponseBody(resp,
                    422,
                    "error",
                    "Invalid parameter name or value");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        JsonObject jsonObject = new JsonParser().parse(RequestBodyParser.parse(req)).getAsJsonObject();
        try {
            Long userId = jsonObject.get("userId").getAsLong();
            Long courseId = jsonObject.get("courseId").getAsLong();
            createdCourseService.remove(CreatedCourse.builder()
                    .userId(userId)
                    .courseId(courseId)
                    .build());
            resp.setStatus(204);
        } catch (NullPointerException e) {
            e.printStackTrace();
            JsonResponseBody.sendResponseBody(resp,
                    422,
                    "error",
                    "Invalid parameter name or value");
        }
    }
}
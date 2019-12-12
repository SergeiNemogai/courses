package controller;

import entity.CreatedCourse;
import service.CreatedCourseService;
import util.JsonConverter;

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
                createdCourses.add(createdCourseService.getById(Long.parseLong(id)));
            }
            writer.println(new JsonConverter().convertToJson(createdCourses, "created_courses"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Long userId = Long.parseLong(req.getParameter("user_id"));
        Long courseId = Long.parseLong(req.getParameter("course_id"));
        createdCourseService.add(CreatedCourse.builder()
                .userId(userId)
                .courseId(courseId)
                .build());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        Long userId = Long.parseLong(req.getParameter("user_id"));
        Long courseId = Long.parseLong(req.getParameter("course_id"));
        createdCourseService.edit(CreatedCourse.builder()
                .userId(userId)
                .courseId(courseId)
                .build());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Long userId = Long.parseLong(req.getParameter("user_id"));
        Long courseId = Long.parseLong(req.getParameter("course_id"));
        createdCourseService.remove(CreatedCourse.builder()
                .userId(userId)
                .courseId(courseId)
                .build());
    }
}
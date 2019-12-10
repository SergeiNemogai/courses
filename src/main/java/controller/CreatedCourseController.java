package controller;

import container.Container;
import entity.CreatedCourse;
import service.CreatedCourseService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CreatedCourseController extends HttpServlet {
    private CreatedCourseService createdCourseService;

    @Override
    public void init() {
        createdCourseService = Container.getCreatedCourseService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String id = req.getParameter("id");
        try (PrintWriter writer = resp.getWriter()) {
            if (id == null) {
                for (CreatedCourse createdCourse : createdCourseService.getAll()) {
                    writer.println("<p>" + createdCourse + "<p>");
                }
            } else {
                writer.println(createdCourseService.getById(Long.parseLong(id)));
            }
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
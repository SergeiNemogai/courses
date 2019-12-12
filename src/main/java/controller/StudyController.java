package controller;

import entity.Study;
import service.StudyService;
import util.JsonConverter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class StudyController extends HttpServlet {
    private final StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String id = req.getParameter("id");
        try (PrintWriter writer = resp.getWriter()) {
            List<Study> studies = new ArrayList<>();
            if (id == null) {
                studies.addAll(studyService.getAll());
            } else {
                studies.add(studyService.getById(Long.parseLong(id)));
            }
            writer.println(new JsonConverter().convertToJson(studies, "studies"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("id"));
        Long courseId = Long.parseLong(req.getParameter("course_id"));
        Long userId = Long.parseLong(req.getParameter("user_id"));
        studyService.add(Study.builder()
                .id(id)
                .courseId(courseId)
                .userId(userId)
                .build());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("id"));
        Long courseId = Long.parseLong(req.getParameter("course_id"));
        Long userId = Long.parseLong(req.getParameter("user_id"));
        studyService.edit(Study.builder()
                .id(id)
                .courseId(courseId)
                .userId(userId)
                .build());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("id"));
        Long courseId = Long.parseLong(req.getParameter("course_id"));
        Long userId = Long.parseLong(req.getParameter("user_id"));
        studyService.remove(Study.builder()
                .id(id)
                .courseId(courseId)
                .userId(userId)
                .build());
    }
}
package controller;

import container.Container;
import entity.Study;
import service.StudyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class StudyController extends HttpServlet {
    private StudyService studyService;

    @Override
    public void init() {
        studyService = Container.getStudyService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String id = req.getParameter("id");
        try (PrintWriter writer = resp.getWriter()) {
            if (id == null) {
                for (Study study : studyService.getAll()) {
                    writer.println("<p>" + study + "</p>");
                }
            } else {
                writer.println(studyService.getById(Long.parseLong(id)));
            }
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
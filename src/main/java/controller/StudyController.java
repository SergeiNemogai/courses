package controller;

import entity.Study;
import service.ServiceFactory;
import service.StudyService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class StudyController extends HttpServlet {
    //TODO: all CRUD operations from StudyService

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        StudyService studyService = ServiceFactory.getStudyService();
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
}
package controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Study;
import service.StudyService;
import util.JsonConverter;
import util.RequestBodyParser;

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
                try {
                    studies.add(studyService.getById(Long.parseLong(id)));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    resp.setStatus(422);
                }
            }
            writer.println(new JsonConverter().convertToJson(studies, "studies"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        JsonObject jsonObject = new JsonParser().parse(RequestBodyParser.parse(req)).getAsJsonObject();
        try {
            Long id = jsonObject.get("id").getAsLong();
            Long courseId = jsonObject.get("courseId").getAsLong();
            Long userId = jsonObject.get("userId").getAsLong();
            studyService.add(Study.builder()
                    .id(id)
                    .courseId(courseId)
                    .userId(userId)
                    .build());
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
            Long courseId = jsonObject.get("courseId").getAsLong();
            Long userId = jsonObject.get("userId").getAsLong();
            studyService.edit(Study.builder()
                    .id(id)
                    .courseId(courseId)
                    .userId(userId)
                    .build());
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
            Long courseId = jsonObject.get("courseId").getAsLong();
            Long userId = jsonObject.get("userId").getAsLong();
            studyService.remove(Study.builder()
                    .id(id)
                    .courseId(courseId)
                    .userId(userId)
                    .build());
            resp.setStatus(204);
        } catch (NullPointerException e) {
            e.printStackTrace();
            resp.setStatus(422);
        }
    }
}
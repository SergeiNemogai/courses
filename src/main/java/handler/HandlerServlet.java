package handler;

import container.IoCContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HandlerServlet extends HttpServlet {
    private HttpServlet controller;
    private final IoCContainer container = new IoCContainer();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if (path.equals("/")) {
            try (PrintWriter writer = resp.getWriter()) {
                writer.println("<h1>" + "Home page. It's OK" + "</h1>");
            }
        } else {
            controller = (HttpServlet) container.getBean(path.substring(1));
            if (controller == null) {
                sendNotFound(resp);
            } else {
                controller.service(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        controller = (HttpServlet) container.getBean(path.substring(1));
        if (controller == null) {
            sendNotFound(resp);
        } else {
            controller.service(req, resp);
            resp.setStatus(201);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        controller = (HttpServlet) container.getBean(path.substring(1));
        if (controller == null) {
            sendNotFound(resp);
        } else {
            controller.service(req, resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        controller = (HttpServlet) container.getBean(path.substring(1));
        if (controller == null) {
            sendNotFound(resp);
        } else {
            controller.service(req, resp);
            resp.setStatus(204);
        }
    }

    private void sendNotFound(HttpServletResponse response) {
        try (PrintWriter writer = response.getWriter()) {
            response.setStatus(404);
            response.setContentType("application/json;charset=UTF-8");
            writer.println("{ \"error\" : \"Resource you've been searching doesn't exist\" }");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
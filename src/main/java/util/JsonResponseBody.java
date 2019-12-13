package util;

import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonResponseBody {
    public static void sendResponseBody(HttpServletResponse response, int statusCode, String status, String comment) {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(statusCode);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(status, comment);
        try (PrintWriter writer = response.getWriter()) {
            writer.println(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
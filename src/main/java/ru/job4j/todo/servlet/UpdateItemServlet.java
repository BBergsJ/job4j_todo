package ru.job4j.todo.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.store.HbmStore;
import ru.job4j.todo.store.Store;

import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class UpdateItemServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        req.setCharacterEncoding("UTF-8");

        try {
            Store store = HbmStore.instOf();
            store.update(Integer.parseInt(req.getParameter("id")));
        } catch (IllegalArgumentException iae) {
            resp.sendError(409);
            return;
        }

        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson("200");
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
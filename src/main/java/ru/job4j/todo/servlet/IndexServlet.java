package ru.job4j.todo.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.store.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class IndexServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();
    private static final Logger LOG = LoggerFactory.getLogger(IndexServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        List<Item> tickets;
        String parametr = req.getParameter("tableMode");
        if (parametr.equals("checked")) {
            tickets = HbmStore.instOf().findAll();
        } else {
            tickets = HbmStore.instOf().findByFlagFalse();
        }
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(tickets);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        req.setCharacterEncoding("UTF-8");
        Item item = new Item(req.getParameter("description"),
                new Timestamp(System.currentTimeMillis()),
                false);
        HbmStore.instOf().create(item);
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(item);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
package ru.job4j.todo.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.store.HbmStore;
import ru.job4j.todo.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CategoryServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        List<Category> categories = HbmStore.instOf().findAllCategories();
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(categories);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}

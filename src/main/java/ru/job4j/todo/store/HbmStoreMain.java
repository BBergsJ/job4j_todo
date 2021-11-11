package ru.job4j.todo.store;

import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.Users;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class HbmStoreMain {
    public static void main(String[] args) {
        Store store = HbmStore.instOf();
        Users user = store.findUserByEmail("goldlike888@gmail.com");

        Item item = store.findById(102);

        List<Category> itemList = item.getCategories();
        for (Category c : itemList) {
            System.out.println(c.getId() + " " + c.getName());
        }
    }
}
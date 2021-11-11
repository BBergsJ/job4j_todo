package ru.job4j.todo.store;

import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.Users;

import java.util.List;

public interface Store {

    Item createItem(Item item, List<String> categories);
    Users createUser(Users user);
    void update(int id);
    void delete(Integer id);
    List<Item> findAll();
    List<Item> findByFlagFalse();
    Item findById(int id);
    Users findUserByEmail(String email);
    List<Category> findAllCategories();
    Category findCatById(int id);
}

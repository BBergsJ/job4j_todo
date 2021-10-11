package ru.job4j.todo.store;

import ru.job4j.todo.model.Item;

import java.util.List;

public interface Store {

    Item create(Item item);
    void update(Item item);
    void delete(Integer id);
    List<Item> findAll();
    List<Item> findByFlagFalse();
    Item findById(int id);
}

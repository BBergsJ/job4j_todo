package ru.job4j.todo.store;

import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.Users;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class HbmStoreMain {
    public static void main(String[] args) {
        Store store = HbmStore.instOf();
        Item item = Item.of("testDescription", false, store.findUserByEmail("goldlike888@gmail.com"));
        store.createItem(item);
    }
}

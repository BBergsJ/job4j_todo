package ru.job4j.todo.store;

import ru.job4j.todo.model.Item;

import java.sql.Timestamp;

public class HbmStoreMain {
    public static void main(String[] args) {
        Store store = HbmStore.instOf();

        System.out.println(store.findById(73).toString());
        System.out.println(store.findById(73).isDone());

        store.update(73);

        System.out.println(store.findById(73).toString());
        System.out.println(store.findById(73).isDone());
    }
}

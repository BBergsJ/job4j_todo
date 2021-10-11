package ru.job4j.todo.store;

import ru.job4j.todo.model.Item;

import java.sql.Timestamp;

public class HbmStoreMain {
    public static void main(String[] args) {
        Store store = HbmStore.instOf();
//        store.create(new Item("new desc test 1", new Timestamp(111111), true));
//        store.create(new Item("new desc test 2", new Timestamp(222222), false));
//        store.create(new Item("new desc test 3", new Timestamp(333333), true));
//        store.create(new Item("new desc test 4", new Timestamp(444444), false));

        System.out.println(store.findById(50).toString());
    }
}

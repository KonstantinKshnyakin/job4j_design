package ru.job4j.menu2.menu;

import ru.job4j.menu2.item.Item;

import java.util.Optional;

public interface IMenu {

    void addChild(String parentName, Item child);

    Optional<Item> get(String name);

    String print();
}

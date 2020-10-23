package ru.job4j.bd;

import ru.job4j.model.Item;

import java.util.List;

public interface ItemDaoInterface {

    void save(Item entity);

    void update(Item entity);

    Item findById(int id);

    void delete(Item entity);

    List<Item> findAll();

    void deleteAll();
}

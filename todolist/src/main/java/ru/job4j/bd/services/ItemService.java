package ru.job4j.bd.services;

import ru.job4j.bd.dao.ItemDao;
import ru.job4j.model.Item;

import java.util.List;

public class ItemService {

    private static ItemDao itemDao;

    public ItemService() {
        itemDao = new ItemDao();
    }

    public void save(Item item) {
        itemDao.save(item);
    }

    public void update(Item item) {
        itemDao.update(item);
    }

    public Item findById(Integer id) {
        return itemDao.findById(id);
    }

    public void delete(Integer id) {
        Item item = itemDao.findById(id);
        itemDao.delete(item);
    }

    public List<Item> findAll() {
        return itemDao.findAll();
    }

    public void deleteAll() {
        itemDao.deleteAll();
    }
}

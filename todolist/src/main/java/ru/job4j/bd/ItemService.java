package ru.job4j.bd;

import ru.job4j.model.Item;

import java.util.List;

public class ItemService {

    private static ItemDao itemDao;

    public ItemService() {
        itemDao = new ItemDao();
    }

    public void persist(Item entity) {
        itemDao.openCurrentSessionWithTransaction();
        itemDao.save(entity);
        itemDao.closeCurrentSessionWithTransaction();
    }

    public void update(Item entity) {
        itemDao.openCurrentSessionWithTransaction();
        itemDao.update(entity);
        itemDao.closeCurrentSessionWithTransaction();
    }

    public Item findById(Integer id) {
        itemDao.openCurrentSession();
        Item Item = itemDao.findById(id);
        itemDao.closeCurrentSession();
        return Item;
    }

    public void delete(Integer id) {
        itemDao.openCurrentSessionWithTransaction();
        Item Item = itemDao.findById(id);
        itemDao.delete(Item);
        itemDao.closeCurrentSessionWithTransaction();
    }

    public List<Item> findAll() {
        itemDao.openCurrentSession();
        List<Item> Items = itemDao.findAll();
        itemDao.closeCurrentSession();
        return Items;
    }

    public void deleteAll() {
        itemDao.openCurrentSessionWithTransaction();
        itemDao.deleteAll();
        itemDao.closeCurrentSessionWithTransaction();
    }
}

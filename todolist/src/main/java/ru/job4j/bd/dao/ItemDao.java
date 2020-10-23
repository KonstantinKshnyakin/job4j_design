package ru.job4j.bd.dao;

import ru.job4j.bd.SessionFactoryLoc;
import ru.job4j.bd.inter.ItemDaoInterface;
import ru.job4j.model.Item;

import java.util.List;

public class ItemDao implements ItemDaoInterface {

    private SessionFactoryLoc sessionFactoryLoc;


    @Override
    public void save(Item item) {
        sessionFactoryLoc.openCurrentSessionWithTransaction();
        sessionFactoryLoc.getCurrentSession().save(item);
        sessionFactoryLoc.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Item item) {
        sessionFactoryLoc.openCurrentSessionWithTransaction();
        sessionFactoryLoc.getCurrentSession().update(item);
        sessionFactoryLoc.closeCurrentSessionWithTransaction();
    }

    @Override
    public Item findById(int id) {
        sessionFactoryLoc.openCurrentSession();
        Item item = sessionFactoryLoc.getCurrentSession().get(Item.class, id);
        sessionFactoryLoc.closeCurrentSession();
        return item;
    }

    @Override
    public void delete(Item item) {
        sessionFactoryLoc.openCurrentSessionWithTransaction();
        sessionFactoryLoc.getCurrentSession().delete(item);
        sessionFactoryLoc.closeCurrentSessionWithTransaction();
    }

    @Override
    public List<Item> findAll() {
        sessionFactoryLoc.openCurrentSession();
        List<Item> items = sessionFactoryLoc.getCurrentSession()
                .createQuery("from Item", Item.class).list();
        sessionFactoryLoc.closeCurrentSession();
        return items;
    }

    public void deleteAll() {
        sessionFactoryLoc.openCurrentSessionWithTransaction();
        sessionFactoryLoc.getCurrentSession()
                .createQuery("delete from Item", Item.class);
        sessionFactoryLoc.closeCurrentSessionWithTransaction();
    }
}

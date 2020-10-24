package ru.job4j.bd.dao;

import org.hibernate.query.Query;
import ru.job4j.bd.SessionFactoryLoc;
import ru.job4j.bd.inter.ItemDaoInterface;
import ru.job4j.model.Item;

import java.util.List;

public class ItemDao implements ItemDaoInterface {

    private final SessionFactoryLoc sessionFactoryLoc;

    public ItemDao() {
        sessionFactoryLoc = new SessionFactoryLoc();
    }

    @Override
    public void save(Item item) {
        sessionFactoryLoc.makeTransaction(session -> session.persist(item));
    }

    @Override
    public void update(Item item) {
        sessionFactoryLoc.makeTransaction(session -> session.update(item));
    }

    @Override
    public Item findById(int id) {
        return sessionFactoryLoc.makeSession(
                session -> {
                    Query<Item> query = session.createQuery(
                            "select i from Item i join fetch i.categories where i.id = :id",
                            Item.class
                    );
                    query.setParameter("id", id);
                    return query.getSingleResult();
                }
        );
    }

    @Override
    public void delete(Item item) {
        sessionFactoryLoc.makeTransaction(session -> session.delete(item));
    }

    @Override
    public List<Item> findAll() {
        return sessionFactoryLoc.makeSession(
                session -> session.createQuery("select i from Item i join fetch i.categories", Item.class).list()
        );
    }

    public void deleteAll() {
        sessionFactoryLoc.makeTransaction(
                session -> session.createQuery("delete from Item", Item.class)
        );
    }
}

package ru.job4j.bd.dao;

import ru.job4j.bd.SessionFactoryLoc;
import ru.job4j.bd.inter.CategoryDaoInterface;
import ru.job4j.model.Category;

import java.util.List;

public class CategoryDao implements CategoryDaoInterface {

    private final SessionFactoryLoc sessionFactoryLoc;

    public CategoryDao() {
        sessionFactoryLoc = new SessionFactoryLoc();
    }

    @Override
    public void save(Category category) {
        sessionFactoryLoc.makeTransaction(session -> session.persist(category));
    }

    @Override
    public void update(Category category) {
        sessionFactoryLoc.makeTransaction(session -> session.update(category));
    }

    @Override
    public Category findById(int id) {
        return sessionFactoryLoc.makeSession(session -> session.get(Category.class, id));
    }

    @Override
    public void delete(Category category) {
        sessionFactoryLoc.makeTransaction(session -> session.delete(category));
    }

    @Override
    public List<Category> findAll() {
        return sessionFactoryLoc.makeSession(
                session -> session.createQuery("from Category", Category.class).list()
        );
    }

    @Override
    public void deleteAll() {
        sessionFactoryLoc.makeTransaction(
                session -> session.createQuery("delete from Category", Category.class)
        );
    }
}

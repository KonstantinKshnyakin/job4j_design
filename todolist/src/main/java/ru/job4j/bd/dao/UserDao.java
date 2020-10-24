package ru.job4j.bd.dao;

import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import ru.job4j.bd.SessionFactoryLoc;
import ru.job4j.bd.inter.UserDaoInterface;
import ru.job4j.model.Item;
import ru.job4j.model.User;

import java.util.List;

public class UserDao implements UserDaoInterface {

    private final SessionFactoryLoc sessionFactoryLoc;

    public UserDao() {
        sessionFactoryLoc = new SessionFactoryLoc();
    }

    @Override
    public void save(User user) {
        sessionFactoryLoc.makeTransaction(session -> session.persist(user));
    }

    @Override
    public void update(User user) {
        sessionFactoryLoc.makeTransaction(session -> session.update(user));
    }

    @Override
    public User findById(int id) {
        return sessionFactoryLoc.makeSession(session -> session.get(User.class, id));
    }

    @Override
    public void delete(User user) {
        sessionFactoryLoc.makeTransaction(session -> session.delete(user));
    }

    @Override
    public List<User> findAll() {
        return sessionFactoryLoc.makeSession(
                session -> {
                    Query<User> query = session.createQuery("select u from User u join fetch u.items", User.class);
                    List<User> list = query.list();
                    for (User user : list) {
                        for (Item item : user.getItems()) {
                            Hibernate.initialize(item.getCategories());
                        }
                    }
                    return list;
                }
        );
    }

    @Override
    public void deleteAll() {
        sessionFactoryLoc.makeTransaction(
                session -> session.createQuery("delete from User", User.class)
        );
    }
}

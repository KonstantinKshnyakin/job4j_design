package ru.job4j.bd.dao;

import ru.job4j.bd.SessionFactoryLoc;
import ru.job4j.bd.inter.UserDaoInterface;
import ru.job4j.model.User;

import java.util.List;

public class UserDao implements UserDaoInterface {

    private SessionFactoryLoc sessionFactoryLoc;

    public UserDao() {
        sessionFactoryLoc = new SessionFactoryLoc();
    }

    @Override
    public void save(User user) {
        sessionFactoryLoc.openCurrentSessionWithTransaction();
        sessionFactoryLoc.getCurrentSession().save(user);
        sessionFactoryLoc.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(User user) {
        sessionFactoryLoc.openCurrentSessionWithTransaction();
        sessionFactoryLoc.getCurrentSession().update(user);
        sessionFactoryLoc.closeCurrentSessionWithTransaction();
    }

    @Override
    public User findById(int id) {
        sessionFactoryLoc.openCurrentSession();
        User user = sessionFactoryLoc.getCurrentSession().get(User.class, id);
        sessionFactoryLoc.closeCurrentSession();
        return user;
    }

    @Override
    public void delete(User user) {
        sessionFactoryLoc.openCurrentSessionWithTransaction();
        sessionFactoryLoc.getCurrentSession().delete(user);
        sessionFactoryLoc.closeCurrentSessionWithTransaction();
    }

    @Override
    public List<User> findAll() {
        sessionFactoryLoc.openCurrentSession();
        List<User> users = sessionFactoryLoc.getCurrentSession()
                .createQuery("from User", User.class).list();
        sessionFactoryLoc.closeCurrentSession();
        return users;
    }

    public void deleteAll() {
        sessionFactoryLoc.openCurrentSessionWithTransaction();
        sessionFactoryLoc.getCurrentSession()
                .createQuery("delete from User", User.class);
        sessionFactoryLoc.closeCurrentSessionWithTransaction();
    }
}

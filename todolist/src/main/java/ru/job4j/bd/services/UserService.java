package ru.job4j.bd.services;

import ru.job4j.bd.dao.UserDao;
import ru.job4j.model.User;

import java.util.List;

public class UserService {

    private static UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public void save(User user) {
        userDao.save(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public User findById(Integer id) {
        return userDao.findById(id);
    }

    public void delete(Integer id) {
        User user = userDao.findById(id);
        userDao.delete(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public void deleteAll() {
        userDao.deleteAll();
    }
}

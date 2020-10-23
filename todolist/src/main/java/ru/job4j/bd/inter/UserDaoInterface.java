package ru.job4j.bd.inter;

import ru.job4j.model.User;

import java.util.List;

public interface UserDaoInterface {

    void save(User user);

    void update(User user);

    User findById(int id);

    void delete(User user);

    List<User> findAll();

    void deleteAll();
}

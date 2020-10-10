package ru.job4j.concurrent.transfer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final HashMap<Integer, User> users = new HashMap<>();
    @GuardedBy("this")
    private int countId;

    public synchronized int add(User user) {
        int id = ++countId;
        users.put(id, User.of(id, user.getAmount()));
        return id;
    }

    public synchronized boolean update(User user) {
        int id = user.getId();
        User updateUser = User.of(id, user.getAmount());
        User putUser = users.put(id, updateUser);
        return putUser != null;
    }

    public synchronized boolean deleted(User user) {
        int id = user.getId();
        User putUser = users.remove(id);
        return putUser != null;
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        User fromUser = users.get(fromId);
        User toUser = users.get(toId);
        int fromUserAmount = fromUser.getAmount() - amount;
        int toUserAmount = toUser.getAmount() + amount;
        users.put(fromId, User.of(fromId, fromUserAmount));
        users.put(toId, User.of(toId, toUserAmount));
    }
}

package ru.job4j.concurrent.transfer;

public class User {

    private int id;
    private int amount;

    private User() {

    }

    public static User of(int id, int amount) {
        User user = new User();
        user.id = id;
        user.amount = amount;
        return user;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }
}

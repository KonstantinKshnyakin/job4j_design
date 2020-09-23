package ru.job4j.strategy.foods;

import java.time.LocalDate;

public class Pizza extends Food {

    public Pizza(String name, LocalDate createDate, LocalDate expireDate, Double price) {
        super(name, createDate, expireDate, price);
    }
}

package ru.job4j.strategy.foods;

import java.time.LocalDate;

public class Cheese extends Food {

    public Cheese(String name, LocalDate createDate, LocalDate expireDate, Double price) {
        super(name, createDate, expireDate, price);
    }
}

package ru.job4j.strategy.foods;

import java.time.LocalDate;

public class Meat extends Food {

    public Meat(String name, LocalDate createDate, LocalDate expireDate, Double price) {
        super(name, createDate, expireDate, price);
    }
}

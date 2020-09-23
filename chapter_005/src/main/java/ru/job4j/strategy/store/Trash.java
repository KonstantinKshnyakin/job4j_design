package ru.job4j.strategy.store;

import ru.job4j.strategy.foods.Food;

import java.util.ArrayList;

public class Trash extends FoodStore {

    public Trash() {
        super.foods = new ArrayList<>();
    }

    @Override
    public boolean addStore(Food food, Double percExpDate) {
        if (percExpDate >= 100) {
            foods.add(food);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Trash{"
                + "foods=" + foods
                + '}';
    }
}

package ru.job4j.strategy.store;

import ru.job4j.strategy.foods.Food;

import java.util.ArrayList;

public class Warehouse extends FoodStore {

    public Warehouse() {
        super.foods = new ArrayList<>();
    }

    @Override
    public boolean addStore(Food food, Double percExpDate) {
        if (percExpDate < 25) {
            foods.add(food);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Warehouse{"
                + "foods=" + foods
                + '}';
    }
}

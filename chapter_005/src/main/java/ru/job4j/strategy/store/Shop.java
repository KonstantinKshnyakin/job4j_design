package ru.job4j.strategy.store;

import ru.job4j.strategy.foods.Food;

import java.util.ArrayList;

public class Shop extends FoodStore {

    public Shop() {
        super.foods = new ArrayList<>();
    }

    @Override
    public boolean addStore(Food food, Double percExpDate) {
        if (percExpDate >= 25 && percExpDate < 75) {
            foods.add(food);
            return true;
        } else if (percExpDate >= 75 && percExpDate < 100) {
            food.setDiscount(0.7);
            foods.add(food);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Shop{"
                + "foods=" + foods
                + '}';
    }
}

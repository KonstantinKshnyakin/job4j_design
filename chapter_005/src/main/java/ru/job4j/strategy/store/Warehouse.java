package ru.job4j.strategy.store;

import ru.job4j.strategy.foods.Food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements FoodStore {

    private final List<Food> foods;

    public Warehouse() {
        foods = new ArrayList<>();
    }

    @Override
    public void addStore(Food food) {
        foods.add(food);
    }

    @Override
    public Food getFood(int index) {
        return foods.get(index);
    }

    @Override
    public boolean acceptByPercentExpDate(Double percExpDate) {
        return percExpDate < 25;
    }

    @Override
    public List<Food> getAllFoods() {
        return new ArrayList<>(this.foods);
    }

    @Override
    public String toString() {
        return "Warehouse{"
                + "foods=" + foods
                + '}';
    }
}
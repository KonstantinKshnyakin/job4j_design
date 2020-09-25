package ru.job4j.strategy.store;

import ru.job4j.strategy.foods.Food;

import java.util.ArrayList;
import java.util.List;

public class Trash implements FoodStore {

    private final List<Food> foods;

    public Trash() {
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
        return percExpDate >= 100;
    }

    @Override
    public List<Food> getAllFoods() {
        return this.foods;
    }

    @Override
    public String toString() {
        return "Trash{"
                + "foods=" + foods
                + '}';
    }
}

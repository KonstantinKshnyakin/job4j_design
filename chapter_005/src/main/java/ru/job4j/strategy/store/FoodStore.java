package ru.job4j.strategy.store;

import ru.job4j.strategy.foods.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class FoodStore {

    protected List<Food> foods;

    public FoodStore() {
        this.foods = new ArrayList<>();
    }

    public abstract boolean addStore(Food food, Double percExpDate);

    public Food getFood(int index) {
        return foods.get(index);
    }

    public List<Food> getAllFoods() {
        return foods;
    }
}

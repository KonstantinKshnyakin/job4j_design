package ru.job4j.strategy.store;

import ru.job4j.strategy.foods.Food;

import java.util.List;

public interface FoodStore {

    void addStore(Food food);

    Food getFood(int index);

    boolean acceptByPercentExpDate(Double percExpDate);

    List<Food> getAllFoods();
}

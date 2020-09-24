package ru.job4j.strategy.store;

import ru.job4j.strategy.foods.Food;

import java.util.ArrayList;
import java.util.List;

public class DiscountShop implements FoodStore {

    private final List<Food> foods;

    public DiscountShop() {
        foods = new ArrayList<>();
    }
    @Override
    public void addStore(Food food) {
        food.setDiscount(0.7);
        foods.add(food);
    }

    @Override
    public Food getFood(int index) {
        return foods.get(index);
    }

    @Override
    public boolean acceptByPercentExpDate(Double percExpDate) {
        return percExpDate >= 75 && percExpDate < 100;
    }

    @Override
    public List<Food> getAllFoods() {
        return new ArrayList<>(this.foods);
    }

    @Override
    public String toString() {
        return "DiscountShop{"
                + "foods=" + foods
                + '}';
    }
}

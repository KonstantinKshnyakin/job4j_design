package ru.job4j.strategy;

import ru.job4j.strategy.foods.Food;

import java.util.List;

public interface IControlQuality {

    void control(List<Food> foods);

    void resort();
}

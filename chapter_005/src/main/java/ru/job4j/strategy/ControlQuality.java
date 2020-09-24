package ru.job4j.strategy;

import ru.job4j.strategy.foods.Food;
import ru.job4j.strategy.store.FoodStore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ControlQuality {

    private final FoodStore[] foodStores;

    public ControlQuality(FoodStore... foodStores) {
        this.foodStores = foodStores;
    }

    public FoodStore[] getFoodStores() {
        return foodStores;
    }

    public void control(List<Food> foods) {
        for (Food food : foods) {
            for (FoodStore foodStore : foodStores) {
                Double percentExpireDate = getPercentExpireDate(food);
                if (foodStore.acceptByPercentExpDate(percentExpireDate)) {
                    foodStore.addStore(food);
                }
            }
        }
    }

    private Double getPercentExpireDate(Food food) {
        LocalDate expireDate = food.getExpireDate();
        LocalDate createDate = food.getCreateDate();
        LocalDate now = LocalDate.now();
        double days = (double) ChronoUnit.DAYS.between(createDate, now);
        double expireDays = (double) ChronoUnit.DAYS.between(createDate, expireDate);
        return days / (expireDays / 100);
    }
}

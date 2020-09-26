package ru.job4j.strategy;

import ru.job4j.strategy.foods.Food;
import ru.job4j.strategy.store.FoodStore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ControlQualityImpl implements IControlQuality {

    private final FoodStore[] foodStores;

    public ControlQualityImpl(FoodStore... foodStores) {
        this.foodStores = foodStores;
    }

    public FoodStore[] getFoodStores() {
        return foodStores;
    }

    @Override
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

    @Override
    public void resort() {
        List<Food> foodsList = new ArrayList<>();
        for (FoodStore foodStore : foodStores) {
            List<Food> allFoods = foodStore.getFoodsAndClear();
            foodsList.addAll(allFoods);
        }
        control(foodsList);
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

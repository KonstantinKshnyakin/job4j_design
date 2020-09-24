package ru.job4j.strategy;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.strategy.foods.Cheese;
import ru.job4j.strategy.foods.Food;
import ru.job4j.strategy.foods.Meat;
import ru.job4j.strategy.foods.Pizza;
import ru.job4j.strategy.store.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;

public class ControlQualityTest {

    @Test
    public void whenUseControlQuality() {
        LocalDate now = LocalDate.now();
        LocalDate meatCreateDate = now.minusDays(3);
        LocalDate meatExpireDate = now.plusDays(27);
        LocalDate cheeseCreateDate = now.minusDays(11);
        LocalDate cheeseExpireDate = now.plusDays(19);
        LocalDate pizzaCreateDate = now.minusDays(27);
        LocalDate pizzaExpireDate = now.plusDays(3);
        LocalDate cheeseExpiredCreateDate = now.minusDays(31);
        LocalDate cheeseExpiredExpireDate = now.minusDays(1);

        Food meat = new Meat("Meat", meatCreateDate, meatExpireDate, 100D);
        Food cheese = new Cheese("Cheese", cheeseCreateDate, cheeseExpireDate, 100D);
        Food pizza = new Pizza("Pizza", pizzaCreateDate, pizzaExpireDate, 100D);
        Food cheesedExpire = new Cheese("Cheese_Expired", cheeseExpiredCreateDate, cheeseExpiredExpireDate, 100D);

        ArrayList<Food> list = new ArrayList<>();
        list.add(meat);
        list.add(cheese);
        list.add(pizza);
        list.add(cheesedExpire);

        ControlQuality controlQuality = new ControlQuality(new Warehouse(), new Shop(), new DiscountShop(), new Trash());
        controlQuality.control(list);

        FoodStore[] foodStores = controlQuality.getFoodStores();
        FoodStore foodStore1 = foodStores[0];
        FoodStore foodStore2 = foodStores[1];
        FoodStore foodStore3 = foodStores[2];
        FoodStore foodStore4 = foodStores[3];

        List<Food> allFoods1 = foodStore1.getAllFoods();
        Assert.assertThat(allFoods1.size(), is(1));
        Assert.assertThat(allFoods1.get(0).getName(), is("Meat"));

        List<Food> allFoods2 = foodStore2.getAllFoods();
        Assert.assertThat(allFoods2.size(), is(1));
        Assert.assertThat(allFoods2.get(0).getName(), is("Cheese"));

        List<Food> allFoods3 = foodStore3.getAllFoods();
        Assert.assertThat(allFoods3.size(), is(1));
        Assert.assertThat(allFoods3.get(0).getName(), is("Pizza"));
        Assert.assertThat(allFoods3.get(0).getDiscount(), is(0.7));

        List<Food> allFoods4 = foodStore4.getAllFoods();
        Assert.assertThat(allFoods4.size(), is(1));
        Assert.assertThat(allFoods4.get(0).getName(), is("Cheese_Expired"));
        System.out.println(Arrays.toString(foodStores));
    }
}

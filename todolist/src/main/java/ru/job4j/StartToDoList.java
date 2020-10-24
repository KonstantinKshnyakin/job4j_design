package ru.job4j;

import ru.job4j.bd.services.UserService;
import ru.job4j.model.Category;
import ru.job4j.model.Item;
import ru.job4j.model.User;

import java.util.Arrays;
import java.util.Collections;

public class StartToDoList {

    public static void main(String[] args) {
        Category category1 = Category.of("Category1");
        Category category2 = Category.of("Category2");
        Category category3 = Category.of("Category3");

        Item item1 = Item.of("description1");
        item1.addCategories(Collections.singletonList(category3));
        Item item2 = Item.of("description1");
        item2.addCategories(Arrays.asList(category2, category1));
        Item item3 = Item.of("description1");
        item3.addCategories(Arrays.asList(category2, category1, category3));

        User user = User.of("firstName", "lastName");
        user.addItems(Arrays.asList(item1, item2, item3));

        UserService userService = new UserService();
        userService.save(user);

        for (User user1 : userService.findAll()) {
            System.out.println(user1);
        }
    }
}

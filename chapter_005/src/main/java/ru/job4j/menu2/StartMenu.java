package ru.job4j.menu2;

import ru.job4j.menu2.item.Item;
import ru.job4j.menu2.menu.Menu;
import ru.job4j.menu2.outer.InputOutput;
import ru.job4j.menu2.outer.InputOutputImpl;

import java.util.Optional;

public class StartMenu {

    public static final String WANT_TO_EXIT = "Select \"exit\" if want to exit.";

    public void init(InputOutput inputOutput, Menu menu) {
        boolean flag = true;
        while (flag) {
            inputOutput.printStr(menu.print());
            inputOutput.printStr(WANT_TO_EXIT);
            String answer = ask(inputOutput);
            if (!isExit(answer)) {
                Optional<Item> item = menu.get(answer);
                item.ifPresent(Item::act);
            } else {
                flag = false;
            }
        }
    }

    private String ask(InputOutput inputOutput) {
        return inputOutput.ask("Select : ");
    }

    private boolean isExit(String str) {
        return str.equals("exit");
    }

    public static void main(String[] args) {
        Item item111 = new Item("1.1.1");
        Item item112 = new Item("1.1.2");
        Item item113 = new Item("1.1.3");
        Item item11 = new Item("1.1");
        Item item1 = new Item("1");
        Menu menu = new Menu(item1);
        menu.addChild(item1.getName(), item11);
        menu.addChild(item11.getName(), item111);
        menu.addChild(item11.getName(), item112);
        menu.addChild(item11.getName(), item113);

        StartMenu startMenu = new StartMenu();
        startMenu.init(new InputOutputImpl(), menu);

    }
}

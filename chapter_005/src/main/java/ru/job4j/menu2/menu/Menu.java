package ru.job4j.menu2.menu;

import org.apache.commons.lang3.StringUtils;
import ru.job4j.menu2.item.Item;

import java.util.*;

public class Menu implements IMenu {

    private final Item parentItem;

    public Menu(Item parentNode) {
        this.parentItem = parentNode;
    }

    @Override
    public void addChild(String parentName, Item child) {
        Optional<Item> treeItem = getTreeItem(this.parentItem, parentName);
        treeItem.ifPresent(item -> item.getChildList().add(child));
    }

    @Override
    public Optional<Item> get(String name) {
        return getTreeItem(this.parentItem, name);
    }

    private Optional<Item> getTreeItem(Item item, String name) {
        if (name.equals(item.getName())) {
            return Optional.of(item);
        } else {
            List<Item> childList = item.getChildList();
            if (childList.size() > 0) {
                for (Item tItem : childList) {
                    Optional<Item> treeItem = getTreeItem(tItem, name);
                    if (treeItem.isPresent()) {
                        if (treeItem.get().getName().equals(name)) {
                            return treeItem;
                        }
                    }
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public String print() {
        return printTree(this.parentItem, 1);
    }

    private String printTree(Item parent, int lvl) {
        StringBuilder builder = new StringBuilder();
        String insert = StringUtils.repeat("-", lvl++);
        builder.append(insert).append(parent.getName()).append(System.lineSeparator());
        List<Item> childList = parent.getChildList();
        if (childList.size() != 0) {
            for (Item child : childList) {
                String tree = printTree(child, lvl);
                builder.append(tree);
            }
        }
        return builder.toString();
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

        System.out.println(menu.print());

        Optional<Item> stringItem = menu.get("1.1.2");
        System.out.println(stringItem.get());

    }
}

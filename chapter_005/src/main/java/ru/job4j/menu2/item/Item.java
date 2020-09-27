package ru.job4j.menu2.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Item implements Action {

    private final String name;
    private final List<Item> childList;

    public Item(String name) {
        this.name = name;
        this.childList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Item> getChildList() {
        return childList;
    }

    public void setChild(Item child) {
        childList.add(child);
    }


    @Override
    public void act() {
        System.out.println("Action : " + this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }

        Item item = (Item) o;

        if (!Objects.equals(name, item.name)) {
            return false;
        }
        return Objects.equals(childList, item.childList);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (childList != null ? childList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{"
                + "name='" + name + '\''
                + ", childList=" + childList
                + '}';
    }
}

package ru.job4j.set;

import ru.job4j.list.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {

    private final SimpleArray<T> simpleArray = new SimpleArray<>();
    private int size = 0;

    public void add(T model) {
        boolean containsElement = checkContainsElement(model);
        if (!containsElement) {
            simpleArray.add(model);
            size++;
        }
    }

    public int size() {
        return size;
    }

    private boolean checkContainsElement(T model) {
        for (T t : simpleArray) {
            if (Objects.equals(t, model)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return simpleArray.iterator();
    }
}

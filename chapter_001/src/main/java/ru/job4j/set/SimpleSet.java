package ru.job4j.set;

import ru.job4j.list.SimpleArray;

import java.util.Iterator;

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
            if (t.equals(model) || model == null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final Iterator<T> iterator = simpleArray.iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                return iterator.next();
            }
        };
    }
}

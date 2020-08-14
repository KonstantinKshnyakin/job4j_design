package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private T[] array;
    private int position = 0;

    public SimpleArray(int capacity) {
        this.array = (T[]) new Object[capacity];
    }

    public void add(T model) {
        array[position++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, position);
        array[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, position);
        if (array.length - index >= 0) {
            System.arraycopy(array, index + 1, array, index, array.length - index - 1);
            position--;
        }
    }

    public T get(int index) {
        Objects.checkIndex(index, position);
        return array[index];
    }

    public int size() {
        return position;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator<>();
    }

    private class SimpleArrayIterator<T> implements Iterator<T> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < position;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) array[cursor++];
        }
    }
}

package ru.job4j.list;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] elementData;
    private int size;
    private int modCount = 0;

    public SimpleArray(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public SimpleArray() {
        this.elementData = new Object[10];
    }


    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T)elementData[index];
    }

    public void add(T model) {
        modCount++;
        checkGrow();
        elementData[size++] = model;
    }

    private void checkGrow() {
        if (size > elementData.length) {
            int newCapacity = (int) (elementData.length * 1.5);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    public T set(int index, T element) {
        Objects.checkIndex(index, size);
        modCount++;
        T oldValue = (T) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    public T remove(int index) {
        Objects.checkIndex(index, size);
        modCount++;
        T oldValue = (T) elementData[index];
        if (size - index >= 0) {
            System.arraycopy(elementData, index + 1, elementData, index, size - index);
            size--;
        }
        return oldValue;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                checkModification();
                return cursor < size;
            }

            @Override
            public T next() {
                checkModification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) elementData[cursor++];
            }

            private void checkModification() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
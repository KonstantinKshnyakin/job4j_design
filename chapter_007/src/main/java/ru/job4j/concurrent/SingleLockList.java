package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.SimpleArray;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("this")
    private final SimpleArray<T> array = new SimpleArray<>();

    public synchronized void add(T value) {
        array.add(value);
    }

    public synchronized T get(int index) {
        return array.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy().iterator();
    }

    private SimpleArray<T> copy() {
        SimpleArray<T> newArray = new SimpleArray<>();
        array.forEach(newArray::add);
        return newArray;
    }
}

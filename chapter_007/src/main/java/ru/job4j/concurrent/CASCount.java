package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {

    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount() {
        count.set(0);
    }

    public void increment() {
        int exValue;
        int newValue;
        do {
            exValue = count.get();
            newValue = exValue + 1;
        } while (!count.compareAndSet(exValue, newValue));
    }

    public int get() {
        return count.get();
    }
}
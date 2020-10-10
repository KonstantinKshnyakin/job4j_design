package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue;
    private final int maxSize;

    public SimpleBlockingQueue(int size) {
        queue = new LinkedList<>();
        this.maxSize = size;
    }

    public synchronized T poll() throws InterruptedException {
        while (this.queue.isEmpty()) {
            this.wait();
        }
        T element = queue.poll();
        this.notifyAll();
        return element;
    }

    public synchronized void offer(T t) throws InterruptedException {
        while (this.queue.size() >= this.maxSize) {
            this.wait();
        }
        this.queue.offer(t);
        this.notifyAll();
    }
}
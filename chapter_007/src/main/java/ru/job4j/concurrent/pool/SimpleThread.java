package ru.job4j.concurrent.pool;

import ru.job4j.concurrent.SimpleBlockingQueue;

public class SimpleThread extends Thread {

    private final SimpleBlockingQueue<Runnable> tasks;

    public SimpleThread(SimpleBlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (!isInterrupted() || !tasks.isEmpty()) {
            try {
                tasks.poll().run();
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}

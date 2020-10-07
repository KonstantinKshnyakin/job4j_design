package ru.job4j.concurrent;

public class ThreadState {

    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        first.start();
        second.start();
        Thread thread = Thread.currentThread();
        Thread.State terminated = Thread.State.TERMINATED;
        while ((first.getState() != terminated) || (second.getState() != terminated)) {
            System.out.printf("%s : %s; %s : %s; %s : %s%n", thread.getName(), thread.getState(),
                                                             first.getName(), first.getState(),
                                                             second.getName(), second.getState());
        }
        System.out.println("Work completed!");
    }
}
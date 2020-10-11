package ru.job4j.concurrent.nonblock;

public class OptimisticException extends RuntimeException {

    public OptimisticException() {
        super("Optimistic Exception");
    }
}

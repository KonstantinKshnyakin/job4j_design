package ru.job4j.park.car;

public class Track extends Car {

    private static final int DEFAULT_SIZE = 3;

    public Track() {
        super(DEFAULT_SIZE);
    }

    public Track(int size) {
        super(size);
    }
}

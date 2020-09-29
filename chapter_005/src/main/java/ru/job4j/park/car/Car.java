package ru.job4j.park.car;

public abstract class Car {

    private final int id;
    private final int size;
    private static int idCount = 0;

    public Car(int size) {
        this.id = ++idCount;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }
}


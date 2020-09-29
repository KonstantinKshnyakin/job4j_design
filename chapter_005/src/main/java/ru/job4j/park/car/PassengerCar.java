package ru.job4j.park.car;

public class PassengerCar extends Car {

    private static final int DEFAULT_SIZE = 1;

    public PassengerCar(int size) {
        super(size);
    }

    public PassengerCar() {
        super(DEFAULT_SIZE);
    }
}

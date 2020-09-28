package ru.job4j.park.parking;

import ru.job4j.park.car.Car;

import java.util.List;

public class Parking implements IParking {

    private final Car[] parkSpace;

    public Parking(int parkSize) {
        this.parkSpace = new Car[parkSize];
    }

    @Override
    public boolean canPark(Car car) {
        return false;
    }

    @Override
    public List<Integer> park(Car car) {
        return null;
    }
}

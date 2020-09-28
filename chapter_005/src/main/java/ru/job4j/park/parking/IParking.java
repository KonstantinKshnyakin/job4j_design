package ru.job4j.park.parking;

import ru.job4j.park.car.Car;

import java.util.List;

public interface IParking {

    boolean canPark(Car car);

    List<Integer> park(Car car);
}

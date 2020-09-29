package ru.job4j.park.parking;

import ru.job4j.park.car.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParkingCar implements IParkingCar {

    private final Car[] parkSpace;
    private int occupiedSpace;

    public ParkingCar(int parkSize) {
        this.occupiedSpace = 0;
        this.parkSpace = new Car[parkSize];
    }

    @Override
    public boolean carLeavePark(Car leaveCar) {
        boolean isLeave = false;
        for (int i = 0; i < parkSpace.length; i++) {
            if (leaveCar.equals(parkSpace[i])) {
                parkSpace[i] = null;
                occupiedSpace--;
                isLeave = true;
            }
        }
        return isLeave;
    }

    private List<Integer> searchParkSpace(Car car) {
        int countSpace = 0;
        List<Integer> spaceNumbersList = new ArrayList<>();
        for (int i = 0; i < parkSpace.length; i++) {
            if (parkSpace[i] == null) {
                countSpace++;
            } else {
                countSpace = 0;
            }
            if (countSpace == car.getSize()) {
                for (int j = 0; j < car.getSize(); j++) {
                    spaceNumbersList.add(i - j);
                }
                return spaceNumbersList;
            }
        }
        return Collections.emptyList();
    }

    @Override
    public boolean canPark(Car car) {
        if (occupiedSpace + car.getSize() <= parkSpace.length) {
            List<Integer> list = searchParkSpace(car);
            return list.size() != 0;
        }
        return false;
    }

    @Override
    public List<Integer> park(Car car) {
        List<Integer> spaceNumbersList = searchParkSpace(car);
        if (spaceNumbersList.size() == 0) {
            throw new IllegalArgumentException();
        }
        for (int index : spaceNumbersList) {
            parkSpace[index] = car;
            occupiedSpace++;
        }
        return spaceNumbersList;
    }
}

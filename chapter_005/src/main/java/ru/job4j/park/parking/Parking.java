package ru.job4j.park.parking;

import ru.job4j.park.car.Car;
import ru.job4j.park.car.Track;

import java.util.List;

public class Parking implements IParkingCar, IParkingTrack{

    private final IParkingCar parkingCar;
    private final IParkingTrack parkingTrack;

    public Parking(int parkPassPark, int parkTrackPark) {
        this.parkingCar = new ParkingCar(parkPassPark);
        this.parkingTrack = new ParkingTrack(parkTrackPark);
    }

    @Override
    public boolean canPark(Car car) {
        return false;
    }

    @Override
    public List<Integer> park(Car car) {
        return null;
    }

    @Override
    public boolean carLeavePark(Car car) {
        return false;
    }

    @Override
    public boolean canParkTrack(Track track) {
        return false;
    }

    @Override
    public List<Integer> parkTrack(Track track) {
        return null;
    }

    @Override
    public boolean trackLeavePark(Track track) {
        return false;
    }
}

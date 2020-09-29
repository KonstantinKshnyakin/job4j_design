package ru.job4j.park.parking;

import ru.job4j.park.car.Car;
import ru.job4j.park.car.Track;

import java.util.List;

public class CombinedParking implements IParkingCar, IParkingTrack {

    private final IParkingCar parkingCar;
    private final IParkingCar parkingTrack;

    public CombinedParking(int parkPassPark, int parkTrackPark) {
        this.parkingCar = new ParkingCar(parkPassPark);
        this.parkingTrack = new ParkingCar(parkTrackPark);
    }

    @Override
    public boolean canPark(Car car) {
        return parkingCar.canPark(car);
    }

    @Override
    public List<Integer> park(Car car) {
        return parkingCar.park(car);
    }

    @Override
    public boolean carLeavePark(Car car) {
        return parkingCar.carLeavePark(car);
    }

    @Override
    public boolean canParkTrack(Track track) {
        boolean canPark = parkingTrack.canPark(track);
        if (!canPark) {
            return parkingCar.canPark(track);
        }
        return true;
    }

    @Override
    public List<Integer> parkTrack(Track track) {
        boolean canPark = parkingTrack.canPark(track);
        if (canPark) {
            return parkingTrack.park(track);
        } else {
            return parkingCar.park(track);
        }
    }

    @Override
    public boolean trackLeavePark(Track track) {
        boolean isLeave = parkingTrack.carLeavePark(track);
        if (!isLeave) {
            return parkingCar.carLeavePark(track);
        }
        return true;
    }
}

package ru.job4j.park.parking;

import ru.job4j.park.car.Track;

import java.util.List;

public class ParkingTrack implements IParkingTrack {

    private final Track[] parkSpace;

    public ParkingTrack(int parkSize) {
        this.parkSpace = new Track[parkSize];
    }

    @Override
    public boolean trackLeavePark(Track track) {
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
}

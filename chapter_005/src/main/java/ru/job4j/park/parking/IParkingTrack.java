package ru.job4j.park.parking;

import ru.job4j.park.car.Track;

import java.util.List;

public interface IParkingTrack {

    boolean canParkTrack(Track track);

    List<Integer> parkTrack(Track track);

    boolean trackLeavePark(Track track);
}

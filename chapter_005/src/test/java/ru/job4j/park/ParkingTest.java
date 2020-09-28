package ru.job4j.park;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.park.car.Track;
import ru.job4j.park.parking.Parking;

import static org.hamcrest.Matchers.is;

public class ParkingTest {

    @Test
    public void whenFullParkingSpace() {
        Parking parking = new Parking(6);
        Track track1 = new Track(3);
        Track track2 = new Track(3);
        parking.park(track1);
        parking.park(track2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMoreCarsThenParkingSpace() {
        Parking parking = new Parking(9);
        Track track1 = new Track(3);
        Track track2 = new Track(3);
        Track track3 = new Track(4);
        parking.park(track1);
        parking.park(track2);
        parking.park(track3);
    }

    @Test
    public void whenMoreCarsThenParkingSpaceCanPark() {
        Parking parking = new Parking(9);
        Track track1 = new Track(3);
        Track track2 = new Track(3);
        Track track3 = new Track(4);
        boolean canPark1 = parking.canPark(track1);
        Assert.assertThat(canPark1, is(true));
        parking.park(track1);
        boolean canPark2 = parking.canPark(track2);
        Assert.assertThat(canPark2, is(true));
        parking.park(track2);
        boolean canPark3 = parking.canPark(track3);
        Assert.assertThat(canPark3, is(false));
    }
}

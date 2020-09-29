package ru.job4j.park;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.park.car.Car;
import ru.job4j.park.car.PassengerCar;
import ru.job4j.park.car.Track;
import ru.job4j.park.parking.CombinedParking;

import static org.hamcrest.Matchers.is;

public class ParkingTest {

    @Test
    public void whenFullParkingSpace() {
        CombinedParking parking = new CombinedParking(3, 6);
        Car car1 = new PassengerCar();
        Car car2 = new PassengerCar();
        Car car3 = new PassengerCar();
        Track track1 = new Track();
        Track track2 = new Track();
        parking.park(car1);
        parking.park(car2);
        parking.park(car3);
        parking.parkTrack(track1);
        parking.parkTrack(track2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMoreCarsThenParkingSpace() {
        CombinedParking parking = new CombinedParking(3, 5);
        Car car1 = new PassengerCar();
        Track track1 = new Track();
        Track track2 = new Track();
        parking.park(car1);
        parking.parkTrack(track1);
        parking.parkTrack(track2);
    }

    @Test
    public void whenMoreCarsThenParkingSpaceButCarLeave() {
        CombinedParking parking = new CombinedParking(3, 5);
        Car car1 = new PassengerCar();
        Track track1 = new Track();
        Track track2 = new Track();
        parking.park(car1);
        parking.parkTrack(track1);
        boolean isLeave = parking.trackLeavePark(track1);
        Assert.assertThat(isLeave, is(true));
        boolean canParkTrack = parking.canParkTrack(track2);
        Assert.assertThat(canParkTrack, is(true));
        parking.parkTrack(track2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMorePassengerCarsThenPassengerParkingSpace() {
        CombinedParking parking = new CombinedParking(2, 6);
        Car car1 = new PassengerCar();
        Car car2 = new PassengerCar();
        Car car3 = new PassengerCar();
        parking.park(car1);
        parking.park(car2);
        parking.park(car3);
    }

    @Test
    public void whenMorePassengerCarsThenPassengerParkingSpaceButCarLeave() {
        CombinedParking parking = new CombinedParking(2, 6);
        Car car1 = new PassengerCar();
        Car car2 = new PassengerCar();
        Car car3 = new PassengerCar();
        parking.park(car1);
        parking.park(car2);
        parking.carLeavePark(car2);
        parking.park(car3);
    }

    @Test
    public void whenMoreTrackCarsThenTrackParkingSpace() {
        CombinedParking parking = new CombinedParking(4, 3);
        Car car1 = new PassengerCar();
        Track track1 = new Track();
        Track track2 = new Track();
        parking.park(car1);
        parking.parkTrack(track1);
        parking.parkTrack(track2);
    }

    @Test
    public void whenFullParkingSpaceCanPark() {
        CombinedParking parking = new CombinedParking(2, 6);
        Car car1 = new PassengerCar();
        Car car2 = new PassengerCar();
        Track track1 = new Track();
        Track track2 = new Track();

        boolean canPark1 = parking.canPark(car1);
        Assert.assertThat(canPark1, is(true));
        parking.park(car1);

        boolean canPark2 = parking.canPark(car2);
        Assert.assertThat(canPark2, is(true));
        parking.park(car2);

        boolean canParkTrack1 = parking.canParkTrack(track1);
        Assert.assertThat(canParkTrack1, is(true));
        parking.parkTrack(track1);

        boolean canParkTrack2 = parking.canParkTrack(track2);
        Assert.assertThat(canParkTrack2, is(true));
        parking.parkTrack(track2);
    }

    @Test
    public void whenMoreCarsThenParkingSpaceCanPark() {
        CombinedParking parking = new CombinedParking(3, 5);
        Car car1 = new PassengerCar();
        Track track1 = new Track();
        Track track2 = new Track();

        boolean canPark1 = parking.canPark(car1);
        Assert.assertThat(canPark1, is(true));
        parking.park(car1);

        boolean canParkTrack1 = parking.canParkTrack(track1);
        Assert.assertThat(canParkTrack1, is(true));
        parking.parkTrack(track1);

        boolean canParkTrack2 = parking.canParkTrack(track2);
        Assert.assertThat(canParkTrack2, is(false));
    }

    @Test
    public void whenMorePassengerCarsThenPassengerParkingSpaceCanPark() {
        CombinedParking parking = new CombinedParking(1, 6);
        Car car1 = new PassengerCar();
        Car car2 = new PassengerCar();

        boolean canPark1 = parking.canPark(car1);
        Assert.assertThat(canPark1, is(true));
        parking.park(car1);

        boolean canPark2 = parking.canPark(car2);
        Assert.assertThat(canPark2, is(false));
    }

    @Test
    public void whenMoreTrackCarsThenTrackParkingSpaceCanPark() {
        CombinedParking parking = new CombinedParking(4, 3);
        Car car1 = new PassengerCar();
        Track track1 = new Track();
        Track track2 = new Track();

        boolean canPark1 = parking.canPark(car1);
        Assert.assertThat(canPark1, is(true));
        parking.park(car1);

        boolean canParkTrack1 = parking.canParkTrack(track1);
        Assert.assertThat(canParkTrack1, is(true));
        parking.parkTrack(track1);

        boolean canParkTrack2 = parking.canParkTrack(track2);
        Assert.assertThat(canParkTrack2, is(true));
    }
}

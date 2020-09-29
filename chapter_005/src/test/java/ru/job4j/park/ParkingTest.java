package ru.job4j.park;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.park.car.Car;
import ru.job4j.park.car.PassengerCar;
import ru.job4j.park.car.Track;
import ru.job4j.park.parking.Parking;

import static org.hamcrest.Matchers.is;

public class ParkingTest {

    @Test
    public void whenFullParkingSpace() {
        Parking parking = new Parking(3, 6);
        Car car1 = new PassengerCar(1);
        Car car2 = new PassengerCar(1);
        Car car3 = new PassengerCar(1);
        Track track1 = new Track(3);
        Track track2 = new Track(3);
        parking.park(car1);
        parking.park(car2);
        parking.park(car3);
        parking.parkTrack(track1);
        parking.parkTrack(track2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMoreCarsThenParkingSpace() {
        Parking parking = new Parking(3, 5);
        Car car1 = new PassengerCar(1);
        Track track1 = new Track(3);
        Track track2 = new Track(3);
        parking.park(car1);
        parking.parkTrack(track1);
        parking.parkTrack(track2);
    }

    @Test
    public void whenMoreCarsThenParkingSpaceButCarLeave() {
        Parking parking = new Parking(3, 5);
        Car car1 = new PassengerCar(1);
        Track track1 = new Track(3);
        Track track2 = new Track(3);
        parking.park(car1);
        parking.parkTrack(track1);
        parking.trackLeavePark(track1);
        parking.parkTrack(track2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMorePassengerCarsThenPassengerParkingSpace() {
        Parking parking = new Parking(2, 6);
        Car car1 = new PassengerCar(1);
        Car car2 = new PassengerCar(1);
        Car car3 = new PassengerCar(1);
        parking.park(car1);
        parking.park(car2);
        parking.park(car3);
    }

    @Test
    public void whenMorePassengerCarsThenPassengerParkingSpaceButCarLeave() {
        Parking parking = new Parking(2, 6);
        Car car1 = new PassengerCar(1);
        Car car2 = new PassengerCar(1);
        Car car3 = new PassengerCar(1);
        parking.park(car1);
        parking.park(car2);
        parking.carLeavePark(car2);
        parking.park(car3);
    }

    @Test
    public void whenMoreTrackCarsThenTrackParkingSpace() {
        Parking parking = new Parking(4, 3);
        Car car1 = new PassengerCar(1);
        Track track1 = new Track(3);
        Track track2 = new Track(3);
        parking.park(car1);
        parking.parkTrack(track1);
        parking.parkTrack(track2);
    }

    @Test
    public void whenFullParkingSpaceCanPark() {
        Parking parking = new Parking(2, 6);
        Car car1 = new PassengerCar(1);
        Car car2 = new PassengerCar(1);
        Track track1 = new Track(3);
        Track track2 = new Track(3);
        parking.park(car1);
        parking.park(car2);
        parking.parkTrack(track1);
        parking.parkTrack(track2);

        boolean canPark1 = parking.canPark(car1);
        Assert.assertThat(canPark1, is(true));
        parking.park(car1);

        boolean canPark2 = parking.canPark(car2);
        Assert.assertThat(canPark2, is(true));
        parking.park(car2);

        boolean canParkTrack1 = parking.canParkTrack(track1);
        Assert.assertThat(canParkTrack1, is(true));
        parking.park(track1);

        boolean canParkTrack2 = parking.canParkTrack(track2);
        Assert.assertThat(canParkTrack2, is(true));
        parking.park(track2);
    }

    @Test
    public void whenMoreCarsThenParkingSpaceCanPark() {
        Parking parking = new Parking(3, 5);
        Car car1 = new PassengerCar(1);
        Track track1 = new Track(3);
        Track track2 = new Track(3);
        parking.park(car1);
        parking.parkTrack(track1);
        parking.parkTrack(track2);

        boolean canPark1 = parking.canPark(car1);
        Assert.assertThat(canPark1, is(true));
        parking.park(car1);

        boolean canParkTrack1 = parking.canParkTrack(track1);
        Assert.assertThat(canParkTrack1, is(true));
        parking.park(track1);

        boolean canParkTrack2 = parking.canParkTrack(track2);
        Assert.assertThat(canParkTrack2, is(false));
    }

    @Test
    public void whenMorePassengerCarsThenPassengerParkingSpaceCanPark() {
        Parking parking = new Parking(1, 6);
        Car car1 = new PassengerCar(1);
        Car car2 = new PassengerCar(1);

        boolean canPark1 = parking.canPark(car1);
        Assert.assertThat(canPark1, is(true));
        parking.park(car1);

        boolean canPark2 = parking.canPark(car2);
        Assert.assertThat(canPark2, is(false));
    }

    @Test
    public void whenMoreTrackCarsThenTrackParkingSpaceCanPark() {
        Parking parking = new Parking(4, 3);
        Car car1 = new PassengerCar(1);
        Track track1 = new Track(3);
        Track track2 = new Track(3);

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

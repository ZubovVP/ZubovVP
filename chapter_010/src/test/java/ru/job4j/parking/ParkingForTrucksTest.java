package ru.job4j.parking;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 29.11.2019.
 */
public class ParkingForTrucksTest {
    private Parking parking = new ParkingForTrucks(3);
    private Auto track = new Track("Track", "track");

    @Test
    public void testAddTruckOnAParkingForTruckAndCheckThisTruck() throws ParkingException {
        boolean result = false;
        for (Auto auto : this.parking.getParking()) {
            if (auto != null && auto == this.track) {
                result = true;
                break;
            }
        }
        assertThat(result, is(false));
        this.parking.park(this.track);
        for (Auto auto : this.parking.getParking()) {
            if (auto != null && auto == this.track) {
                result = true;
                break;
            }
        }
        assertThat(result, is(true));
    }

    @Test
    public void testCleanTruckFromAParkingForTruckAndCheckThisTruck() throws ParkingException {
        boolean result = false;
        this.parking.park(this.track);
        for (Auto auto : this.parking.getParking()) {
            if (auto != null && auto == this.track) {
                result = true;
                break;
            }
        }
        assertThat(result, is(true));
        this.parking.clean(this.track);
        for (Auto auto : this.parking.getParking()) {
            if (auto != null && auto == this.track) {
                result = false;
                break;
            }
        }
        assertThat(result, is(true));
    }


    @Test(expected = ParkingException.class)
    public void testAddOverTruckOnAParkingForTruckExpectException() throws ParkingException {
        this.parking.park(this.track);
        this.parking.park(this.track);
        this.parking.park(this.track);
        this.parking.park(this.track);
    }

    @Test(expected = ArrayStoreException.class)
    public void testAddCarOnAParkingForTruckExpectException() throws ParkingException {
        Auto car = new Car("Car", "car");
        this.parking.park(car);
    }

}
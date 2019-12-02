package ru.job4j.parking;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 02.12.2019.
 */
public class ParkingForCarsTest {
    private Parking parking = new ParkingForCars(3);
    private Auto car = new Car("Car", "car");
    private Auto truck = new Track("Truck", "truck");

    @Test
    public void addCarOnAParkingAndCheckCar() throws ParkingException {
        boolean result = false;
        for (Auto auto : this.parking.getParking()) {
            if (auto != null && auto == this.car) {
                result = true;
                break;
            }
        }
        assertThat(result, is(false));
        this.parking.park(this.car);
        for (Auto auto : this.parking.getParking()) {
            if (auto != null && auto == this.car) {
                result = true;
                break;
            }
        }
        assertThat(result, is(true));
    }

    @Test
    public void addTruckOnAParkingAndCheckCar() throws ParkingException {
        boolean result = false;

        for (Auto auto : this.parking.getParking()) {
            if (auto != null && auto == this.truck) {
                result = true;
                break;
            }
        }
        assertThat(result, is(false));
        this.parking.park(this.truck);
        for (Auto auto : this.parking.getParking()) {
            if (auto != null && auto == this.truck) {
                result = true;
                break;
            }
        }
        assertThat(result, is(true));
    }

    @Test
    public void testCleanCarFromAParkingForTruckAndCheckThisCar() throws ParkingException {
        boolean result = false;
        this.parking.park(this.car);
        for (Auto auto : this.parking.getParking()) {
            if (auto != null && auto == this.car) {
                result = true;
                break;
            }
        }
        assertThat(result, is(true));
        this.parking.clean(this.car);
        for (Auto auto : this.parking.getParking()) {
            if (auto != null && auto == this.car) {
                result = false;
                break;
            }
        }
        assertThat(result, is(true));
    }

    @Test(expected = ParkingException.class)
    public void testAddOverTruckOnAParkingForTruckExpectException() throws ParkingException {
        this.parking.park(this.truck);
        this.parking.park(this.truck);
        this.parking.park(this.truck);
    }
}
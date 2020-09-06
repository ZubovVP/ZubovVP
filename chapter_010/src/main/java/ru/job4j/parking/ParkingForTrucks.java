package ru.job4j.parking;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 23.11.2019.
 */
public class ParkingForTrucks extends Parking<Auto> {
    public ParkingForTrucks(int size) {
        super(size);
    }

    /**
     * Park Auto on a parking.
     * If parking car, that 1 place = 1 truck.
     * You don't park car on this parking.
     *
     * @param auto - auto. Class extends abstract class Auto.
     * @return - result.
     * @throws ParkingException - throw if parking is full.
     */
    @Override
    public boolean park(Auto auto) throws ParkingException {
        boolean result = false;
        if (checkCar(auto)) {
            for (int x = 0; x < this.getParking().length; x++) {
                if (x == this.getParking().length - 1 && this.getParking()[x] != null) {
                    throw new ParkingException("Parking is full");
                }
                if (this.getParking()[x] == null) {
                    this.getParking()[x] = auto;
                    result = true;
                    break;
                }
            }
        } else {
            throw new ArrayStoreException("Sorry, this car is'n track. Please, select the correct parking.");
        }
        return result;
    }

    /**
     * Delete auto from a parking.
     *
     * @param auto - auto.
     * @return - result.
     */
    @Override
    public boolean clean(Auto auto) {
        for (int x = 0; x < this.getParking().length; x++) {
            if (this.getParking()[x].hashCode() == auto.hashCode()) {
                this.getParking()[x] = null;
                break;
            }
        }
        return true;
    }

    /**
     * Auto is a truck.
     *
     * @param auto - auto.
     * @return - result.
     */
    private boolean checkCar(Auto auto) {
        return auto instanceof Track;
    }
}

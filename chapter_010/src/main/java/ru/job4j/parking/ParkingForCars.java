package ru.job4j.parking;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 23.11.2019.
 */
public class ParkingForCars extends Parking<Auto> {

    /**
     * Constructor.
     *
     * @param size - size of parking.
     */
    public ParkingForCars(int size) {
        super(size);
    }

    /**
     * Park Auto on a parking.
     * If parking car, that 1 place = 1 car.
     * If parking truck, that 2 place = 1 truck.
     *
     * @param auto - auto. Class extends abstract class Auto.
     * @return - result.
     * @throws ParkingException - throw if parking is full.
     */
    public boolean park(Auto auto) throws ParkingException {
        boolean result = false;
        if (!isTrack(auto)) {
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
            for (int x = 0; x < this.getParking().length; x++) {
                if (x == this.getParking().length - 2 && this.getParking()[x] != null) {
                    throw new ParkingException("Parking is full");
                }
                if (this.getParking()[x] == null && x != (this.getParking().length - 1) && this.getParking()[x + 1] == null) {
                    this.getParking()[x] = auto;
                    this.getParking()[x + 1] = auto;
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Delete auto from a parking.
     *
     * @param auto - auto.
     * @return - result.
     */
    public boolean clean(Auto auto) {
        for (int x = 0; x < this.getParking().length; x++) {
            if (this.getParking()[x].hashCode() == auto.hashCode()) {
                this.getParking()[x] = null;
                if (isTrack(auto)) {
                    break;
                } else {
                    this.getParking()[x + 1] = null;
                    break;
                }
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
    private boolean isTrack(Auto auto) {
        return (auto instanceof Track);
    }
}

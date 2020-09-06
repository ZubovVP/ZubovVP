package ru.job4j.parking;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 24.11.2019.
 */
public abstract class Parking<E> {
    private final E[] list;

    /**
     * Constructor.
     *
     * @param size - size of raking.
     */
    public Parking(int size) {
        this.list = (E[]) new Object[size];
    }

    /**
     * Take array of cars.
     *
     * @return - array.
     */

    public abstract boolean park(E car) throws ParkingException;

    public abstract boolean clean(E car);

    public E[] getParking() {
        return this.list;
    }
}

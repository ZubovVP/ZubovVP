package ru.job4j.parking;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 24.11.2019.
 */
public abstract class Parking implements Actions {
    private final Auto[] list;

    /**
     * Constructor.
     *
     * @param size - size of raking.
     */
    public Parking(int size) {
        this.list = new Auto[size];
    }

    /**
     * Take array of cars.
     *
     * @return - array.
     */
    @Override
    public Auto[] getParking() {
        return this.list;
    }
}

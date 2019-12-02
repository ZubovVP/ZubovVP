package ru.job4j.parking;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 23.11.2019.
 */
public interface Actions {
    boolean park(Auto car) throws ParkingException;

    boolean clean(Auto car);

    Auto[] getParking();
}

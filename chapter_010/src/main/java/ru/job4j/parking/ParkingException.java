package ru.job4j.parking;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 02.12.2019.
 */
public class ParkingException extends Throwable {
    ParkingException(String description) {
        super(description);
    }
}


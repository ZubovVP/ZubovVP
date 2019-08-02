package ru.job4j.service;

import ru.job4j.models.Seat;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 02.08.2019.
 */
public interface Actions<S> {
    List<S> getSeats();

    Seat getSeat(int id);

    void reserveSeat(int id, String status);

    void paySeat(int id, String status, String userName, String phone);

    void deleteReserve();
}

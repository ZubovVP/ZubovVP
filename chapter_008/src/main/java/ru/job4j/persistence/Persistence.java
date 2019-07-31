package ru.job4j.persistence;

import ru.job4j.models.Account;
import ru.job4j.models.Seat;
import ru.job4j.service.DBAccounts;
import ru.job4j.service.DBHalls;

import java.util.Collections;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 15.07.2019.
 */
public class Persistence {
    private static final Persistence INSTANCE = new Persistence();
    private static DBAccounts dbAccounts = DBAccounts.getInstance();
    private static DBHalls dbHalls = DBHalls.getInstance();

    public static Persistence getInstance() {
        return INSTANCE;
    }

    /**
     * Constructor.
     */
    private Persistence() {
    }

    /**
     * Get all seats from  database.
     *
     * @return List of seats.
     */
    public List<Seat> getSeats() {
        List<Seat> result = dbHalls.getPlaces();
        Collections.sort(result);
        return result;
    }

    /**
     * Get seat.
     *
     * @param id - id of seat.
     * @return - seat.
     */
    public Seat getSeat(int id) {
        return dbHalls.getSeat(id);
    }

    public void reserveSeat(int id, String status) {
        Seat seat = dbHalls.getSeat(id);
        seat.setStatus(status);
        dbHalls.reserve(seat);
    }

    /**
     * Pay seat.
     *
     * @param id       - id of seat.
     * @param status   - status.
     * @param userName - userName.
     * @param phone    - phone of user.
     */
    public void paySeat(int id, String status, String userName, String phone) {
        Account account = checkAccount(phone);
        if (account == null) {
            account = new Account(userName, phone);
            account = dbAccounts.add(account);
        }
        Seat seat = dbHalls.getSeat(id);
        seat.setStatus(status);
        seat.setIdUser(account.getId());
        dbHalls.reserve(seat);
    }

    /**
     * Delete status reserve from database.
     */
    public void deleteReserve() {
        dbHalls.deleteReserve();
    }

    /**
     * Check account ih the database.
     *
     * @param phone - phone of account.
     * @return - account.
     */
    private Account checkAccount(String phone) {
        Account result;
        result = dbAccounts.get(phone);
        return result;
    }
}

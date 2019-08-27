package ru.job4j.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Account;
import ru.job4j.models.Seat;
import ru.job4j.persistence.DBAccounts;
import ru.job4j.persistence.DBHalls;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 31.07.2019.
 */
public class DBHallsTest {
    private DBHalls dbHalls;
    private Account account = new Account("Duke", "123");
    private DBAccounts dbAccounts;

    @Before
    public void start() {
        this.dbHalls = DBHalls.getInstance();
        this.dbAccounts = DBAccounts.getInstance();
    }

    @After
    public void finish() {
        this.dbAccounts.clearAll();
    }

    @Test
    public void getInstance() {
        assertThat(dbHalls.getClass().getSimpleName(), is("DBHalls"));
    }

    @Test
    public void getPlacesAndCheckQuantity() {
        assertThat(this.dbHalls.getPlaces().size(), is(9));
    }

    @Test
    public void reserveSeatAndCheck() {
        boolean result = false;
        Seat seat = dbHalls.getSeat(1);
        seat.setStatus("reserve");
        for (Seat seats : this.dbHalls.getPlaces()) {
            if (seats.equals(seat)) {
                result = true;
                return;
            }
        }
        assertThat(result, is(false));
        this.dbHalls.reserve(seat.getId());
        for (Seat seats2 : this.dbHalls.getPlaces()) {
            if (seats2.getRow() == seat.getRow() && seats2.getSeat() == seat.getSeat() && seats2.getStatus().equals(seat.getStatus())) {
                result = true;
                return;
            }
        }
        assertThat(result, is(true));
        this.dbHalls.deleteReserve();
    }

    @Test
    public void reserveAlreadyReservedSeat() {
        boolean result = false;
        Seat seat = dbHalls.getSeat(1);
        seat.setStatus("reserve");
        result = this.dbHalls.reserve(seat.getId());
        assertThat(result, is(true));
        result = this.dbHalls.reserve(seat.getId());
        assertThat(result, is(false));
        this.dbHalls.deleteReserve();
    }

    @Test
    public void soldSeatAndCheck() {
        this.account = dbAccounts.add(this.account);
        boolean result = false;
        Seat seat = dbHalls.getSeat(1);
        seat.setIdUser(this.account.getId());
        seat.setStatus("sold");
        for (Seat seats : this.dbHalls.getPlaces()) {
            if (seats.equals(seat)) {
                result = true;
                return;
            }
        }
        assertThat(result, is(false));
        this.dbHalls.sold(seat.getId(), seat.getIdUser());
        for (Seat seats2 : this.dbHalls.getPlaces()) {
            if (seats2.getRow() == seat.getRow() && seats2.getSeat() == seat.getSeat() && seats2.getStatus().equals(seat.getStatus())) {
                result = true;
                return;
            }
        }
        assertThat(result, is(true));
        this.dbHalls.deleteReserve();
    }

    @Test
    public void getSeatAndCheck() {
        Seat seat = this.dbHalls.getSeat(1);
        assertThat(seat, is(this.dbHalls.getSeat(1)));
        Seat seat1 = this.dbHalls.getSeat(2);
        boolean result = true;
        if (seat.getRow() == seat1.getRow() && seat.getSeat() == seat1.getSeat()) {
            result = false;
        }
        assertTrue(result);
    }

    @Test
    public void deleteReserveAndCheck() {
        boolean result = false;
        this.dbHalls.reserve(2);
        for (Seat seat : this.dbHalls.getPlaces()) {
            if (seat.getStatus().equals("reserve")) {
                result = true;
                return;
            }
        }
        assertTrue(result);

        this.dbHalls.deleteReserve();
        for (Seat seat : this.dbHalls.getPlaces()) {
            if (seat.getStatus().equals("reserve")) {
                result = false;
                return;
            }
            assertTrue(result);
        }

        this.dbHalls.sold(2, this.account.getId());
        for (Seat seat : this.dbHalls.getPlaces()) {
            if (seat.getStatus().equals("sold")) {
                result = false;
                return;
            }
        }
        assertFalse(result);

        this.dbHalls.deleteReserve();
        for (Seat seat : this.dbHalls.getPlaces()) {
            if (seat.getStatus().equals("sold")) {
                result = true;
                return;
            }
            assertTrue(result);
        }
    }
}
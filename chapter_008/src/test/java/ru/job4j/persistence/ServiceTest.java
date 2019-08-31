package ru.job4j.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Account;
import ru.job4j.models.Seat;
import ru.job4j.service.Service;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 31.07.2019.
 */
public class ServiceTest {
    private Service persistence;

    @Before
    public void start() {
        persistence = Service.getInstance();
    }

    @After
    public void finish() {
        DBHalls.getInstance().clearAll();
        DBAccounts.getInstance().clearAll();
    }

    @Test
    public void getInstance() {
        assertThat(this.persistence.getClass().getSimpleName(), is("Service"));
    }

    @Test
    public void getSeats() {
        List<Seat> seats = persistence.getSeats();
        assertThat(seats.size(), is(9));
    }

    @Test
    public void getSeat() {
        Seat seat = persistence.getSeat(1);
        assertThat(seat.getId(), is(1));
        assertThat(seat.getRow(), is(1));
        assertThat(seat.getSeat(), is(1));
    }

    @Test
    public void reserveSeat() {
        Seat seat = persistence.getSeat(2);
        assertThat(seat.getStatus(), is("free"));
        seat.setStatus("reserve");
        persistence.reserveSeat(seat.getId(), seat.getStatus());
        assertThat(persistence.getSeat(2), is(seat));
        persistence.deleteReserve();
    }

    @Test
    public void paySeat() {
        Seat seat = persistence.getSeat(2);
        Account account = new Account("Duke", "123");
        seat.setStatus("sold");
        persistence.paySeat(seat.getId(), seat.getStatus(), account.getName(), account.getPhone());
        seat = persistence.getSeat(2);
        assertThat(persistence.getSeat(2), is(seat));
        seat.setStatus("reserve");
        persistence.reserveSeat(seat.getId(), seat.getStatus());
        persistence.deleteReserve();
    }

    @Test
    public void deleteReserve() {
        Seat seat = persistence.getSeat(2);
        seat.setStatus("reserve");
        persistence.reserveSeat(seat.getId(), seat.getStatus());
        assertThat(persistence.getSeat(2), is(seat));
        persistence.deleteReserve();
        seat.setStatus("free");
        assertThat(persistence.getSeat(2), is(seat));
    }
}
package ru.job4j.servlets;

import org.junit.After;
import org.junit.Test;
import ru.job4j.models.Seat;
import ru.job4j.service.Service;
import ru.job4j.persistence.DBAccounts;
import ru.job4j.persistence.DBHalls;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 01.08.2019.
 */
public class ControllerTest {
    private Service persistence = Service.getInstance();

    @After
    public void finish() {
        DBHalls.getInstance().clearAll();
        DBAccounts.getInstance().clearAll();
    }

    @Test
    public void doGet() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        List<Seat> seats = persistence.getSeats();
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        try {
            when(resp.getWriter()).thenReturn(printWriter);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        new Controller().doGet(req, resp);
        printWriter.flush();
        for (Seat seat : seats) {
            assertTrue(stringWriter.toString().contains(String.valueOf(seat.getId())));
            assertTrue(stringWriter.toString().contains(String.valueOf(seat.getSeat())));
        }
    }
}
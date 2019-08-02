package ru.job4j.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import ru.job4j.models.Seat;
import ru.job4j.persistence.Persistence;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 14.07.2019.
 */
public class HallServlet extends HttpServlet {
    private Persistence persistence = Persistence.getInstance();


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*"); // Cros
        int id = Integer.parseInt(req.getParameter("id"));
        Seat seat = persistence.getSeat(id);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(seat);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*"); // Cros
        int id = Integer.parseInt(req.getParameter("id"));
        String status = req.getParameter("status");
        if (req.getParameter("userName") == null && req.getParameter("phone") == null) {
            persistence.reserveSeat(id, status);
        } else {
            persistence.paySeat(id, status, req.getParameter("userName"), req.getParameter("phone"));
        }
    }
}
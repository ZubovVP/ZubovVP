package ru.job4j.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import ru.job4j.models.Seat;
import ru.job4j.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 19.07.2019.
 */
public class Controller extends HttpServlet {
    private Service persistence = Service.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*"); // Cros
        List<Seat> seats = persistence.getSeats();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(seats);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        writer.flush();
    }

    @Override
    public void destroy() {
        this.persistence.close();
        super.destroy();
    }
}

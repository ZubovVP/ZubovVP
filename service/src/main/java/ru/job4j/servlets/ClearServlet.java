package ru.job4j.servlets;

import ru.job4j.storage.Actions;
import ru.job4j.storage.Operator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 21.10.2019.
 */
public class ClearServlet extends HttpServlet {
    private Actions operator = Operator.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*"); // Cros
        this.operator.clearAll();
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}

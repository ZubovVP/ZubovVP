package ru.job4j.servlets;

import ru.job4j.storage.IncorrectDateException;
import ru.job4j.storage.Store;
import ru.job4j.storage.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov
 * Email: Zubov.VP@yandex.ru
 * Version: $Id$
 * Date: 14.12.2018
 */
public class UserDeleteServlet extends HttpServlet {
    private final Store vs = ValidateService.getInstance();

    /**
     * Delete the user from DB abd redirect on the home.
     *
     * @param req  - HttpServletRequest.
     * @param resp - HttpServletResponse.
     * @throws IOException - IOException.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            this.vs.delete(Integer.parseInt(req.getParameter("id")));
        } catch (IncorrectDateException e) {
            e.getMessage();
            resp.sendError(406, e.getMessage());
        }
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }

    /**
     * Close ValidateService adn destroy FindByIdServlet.
     */
    @Override
    public void destroy() {
        this.vs.close();
        super.destroy();
    }
}
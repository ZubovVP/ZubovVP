package ru.job4j.servlets;

import ru.job4j.storage.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov
 * Email: Zubov.VP@yandex.ru
 * Version: $Id$
 * Date: 03.12.2018
 */
public class UsersController extends HttpServlet {
    private final ValidateService vs = ValidateService.getInstance();

    /**
     * Shows all users. Request UsersViews.jsp.
     *
     * @param req  - HttpServletRequest.
     * @param resp - HttpServletResponse.
     * @throws IOException - IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("users", ValidateService.getInstance().findAll());
        req.getRequestDispatcher("WEB-INF/views/UsersViews.jsp").forward(req, resp);
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
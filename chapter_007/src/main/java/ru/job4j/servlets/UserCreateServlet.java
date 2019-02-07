package ru.job4j.servlets;

import ru.job4j.models.User;
import ru.job4j.storage.IncorrectDateException;
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
 * Date: 14.12.2018
 */
public class UserCreateServlet extends HttpServlet {
    private final ValidateService vs = ValidateService.getInstance();

    /**
     * Request on the Create.jsp.
     *
     * @param req  - request.
     * @param resp - response.
     * @throws ServletException - ServletException.
     * @throws IOException      - IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/Create.jsp").forward(req, resp);
    }

    /**
     * Create user and add the user in the DB. Redirect on the home.
     *
     * @param req  - HttpServletRequest.
     * @param resp - HttpServletResponse.
     * @throws IOException - IOException.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        try {
            this.vs.add(user);
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

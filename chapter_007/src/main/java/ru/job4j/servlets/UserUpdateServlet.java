package ru.job4j.servlets;

import ru.job4j.models.Admin;
import ru.job4j.models.User;
import ru.job4j.models.Viewer;
import ru.job4j.storage.IncorrectDateException;
import ru.job4j.storage.Store;
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
public class UserUpdateServlet extends HttpServlet {
    private final Store vs = ValidateService.getInstance();

    /**
     * Request Update.jsp.
     *
     * @param req  - HttpServletRequest.
     * @param resp - HttpServletResponse.
     * @throws ServletException - ServletException.
     * @throws IOException      - ServletException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userName", req.getParameter("userName"));
        req.setAttribute("login", req.getParameter("login"));
        req.setAttribute("email", req.getParameter("email"));
        req.setAttribute("password", req.getParameter("password"));
        req.setAttribute("role", req.getParameter("role"));
        req.getRequestDispatcher("WEB-INF/views/Update.jsp").forward(req, resp);
    }

    /**
     * Update the user in the DB. Redirect home.
     *
     * @param req  - HttpServletRequest.
     * @param resp - HttpServletResponse.
     * @throws IOException - IOException.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User us = null;
        if (req.getParameter("role").equals("admin")) {
            us = new Admin(Integer.parseInt(req.getParameter("id")), req.getParameter("name"), req.getParameter("login"), req.getParameter("email"), req.getParameter("password"));
        } else if (req.getParameter("role").equals("viewer")) {
            us = new Viewer(Integer.parseInt(req.getParameter("id")), req.getParameter("name"), req.getParameter("login"), req.getParameter("email"), req.getParameter("password"));
        }
        try {
            this.vs.update(us);
        } catch (IncorrectDateException e) {
            e.getMessage();
            resp.sendError(406, e.getMessage());
            return;
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

package ru.job4j.servlets;

import ru.job4j.models.User;
import ru.job4j.storage.Store;
import ru.job4j.storage.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov
 * Email: Zubov.VP@yandex.ru
 * Version: $Id$
 * Date: 03.12.2018
 */
public class UsersController extends HttpServlet {
    private final Store vs = ValidateService.getInstance();

    /**
     * Shows all users. Request UsersViews.jsp.
     *
     * @param req  - HttpServletRequest.
     * @param resp - HttpServletResponse.
     * @throws IOException - IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        if (session.getAttribute("login") == null) {
            resp.sendRedirect(String.format("%s/signin", req.getContextPath()));
        } else {
            if (session.getAttribute("role").equals("admin")) {
                req.setAttribute("users", ValidateService.getInstance().findAll());
            } else if (session.getAttribute("role").equals("viewer")) {
                req.setAttribute("users", ValidateService.getInstance().findAll());
                List<User> user = new ArrayList<>();
                user.add(ValidateService.getInstance().findByLogin((String) session.getAttribute("login")));
                req.setAttribute("users", user);
            }
            req.getRequestDispatcher("WEB-INF/views/UsersViews.jsp").forward(req, resp);
        }
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
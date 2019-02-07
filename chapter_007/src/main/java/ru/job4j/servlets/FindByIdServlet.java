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
 * Date: 14.12.2018
 */
public class FindByIdServlet extends HttpServlet {
    private final ValidateService vs = ValidateService.getInstance();

    /**
     * Find the user form DB and request on the FindById.jsp.
     *
     * @param req  - HttpServletRequest.
     * @param resp - HttpServletResponse.
     * @throws ServletException - ServletException.
     * @throws IOException      - IOException.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", this.vs.findById(Integer.parseInt(req.getParameter("id"))));
        req.getRequestDispatcher("WEB-INF/views/FindById.jsp").forward(req, resp);
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

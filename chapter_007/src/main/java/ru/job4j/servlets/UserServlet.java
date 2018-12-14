package ru.job4j.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov
 * Email: Zubov.VP@yandex.ru
 * Version: $Id$
 * Date: 03.12.2018
 */
public class UserServlet extends HttpServlet {
    private final ValidateService vs = ValidateService.getInstance();

    /**
     * Shows all users.
     *
     * @param req  - HttpServletRequest.
     * @param resp - HttpServletResponse.
     * @throws IOException - IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        for (User user : vs.findAll()) {
            writer.append("Id = ").append(String.valueOf(user.getId()));
            writer.append("; Name = ").append(user.getName());
            writer.append("; Login = ").append(user.getLogin());
            writer.append(" Email = ").append(user.getEmail());
            writer.append("; Create Time = ").append(String.valueOf(user.getCreateDate()));
            writer.append("\n");
        }
        writer.flush();
    }

    /**
     * Add or update or delete user from storage.
     *
     * @param req  - HttpServletRequest.
     * @param resp - HttpServletResponse.
     * @throws IOException - IOException.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if (action.equals("add")) {
            User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
            try {
                this.vs.add(user);
            } catch (IncorrectDateException e) {
                e.getMessage();
                resp.sendError(406, e.getMessage());
            }
        } else if (action.equals("delete")) {
            try {
                this.vs.delete(Integer.parseInt(req.getParameter("id")));
            } catch (IncorrectDateException e) {
                e.getMessage();
                resp.sendError(406, e.getMessage());
            }
        } else if (action.equals("update")) {
            User user = new User(Integer.parseInt(req.getParameter("id")), req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
            try {
                vs.update(user);
            } catch (IncorrectDateException e) {
                e.getMessage();
                resp.sendError(406, e.getMessage());
            }
        } else if (action.equals("findById")) {
            User result = null;
            try {
                result = this.vs.findById(Integer.parseInt(req.getParameter("id")));
            } catch (IncorrectDateException e) {
                e.getMessage();
                resp.sendError(406, e.getMessage());
            }
            resp.setContentType("text/html");
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append("Id = ").append(String.valueOf(result.getId()));
            writer.append("; Name = ").append(result.getName());
            writer.append("; Login = ").append(result.getLogin());
            writer.append(" Email = ").append(result.getEmail());
            writer.append("; Create Time = ").append(String.valueOf(result.getCreateDate()));
            writer.append("\n");
            writer.flush();
        }
    }
}
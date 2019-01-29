package ru.job4j.servlets;

import javax.servlet.ServletException;
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
public class UsersController extends HttpServlet {
    private final ValidateService vs = ValidateService.getInstance();

    /**
     * Shows all users.
     *
     * @param req  - HttpServletRequest.
     * @param resp - HttpServletResponse.
     * @throws IOException - IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("WEB-INF/views/UsersViews.jsp").forward(req, resp);
    }

    /**
     * Add or update or delete user from storage.
     *
     * @param req  - HttpServletRequest.
     * @param resp - HttpServletResponse.
     * @throws IOException - IOException.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        if (action.equals("delete")) {
            try {
                this.vs.delete(Integer.parseInt(req.getParameter("id")));
            } catch (IncorrectDateException e) {
                e.getMessage();
                resp.sendError(406, e.getMessage());
            }
            doGet(req, resp);
        } else if (action.equals("update")) {
            req.getRequestDispatcher("/UserUpdateServlet").forward(req, resp);
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
            StringBuilder sb = new StringBuilder(getHeaderTable());
            sb.append("<tr>");
            sb.append(fillTable(result));
            sb.append("<br>");
            sb.append("<td>");
            sb.append(getCorrectAndDelete(result, req));

            writer.append("<!DOCTYPE html>");
            writer.append("<html lang='en'>");
            writer.append("<head>");
            writer.append("    <meta charset='UTF-8'>");
            writer.append("    <title>Users</title>");
            writer.append("</head>");
            writer.append("<body>");
            writer.append(sb.toString());
            writer.append("</body>");
            writer.append("</html>");
            writer.flush();
        }
    }

    private String getHeaderTable() {
        StringBuilder sb = new StringBuilder("<table align='left' width='100%' border='0'> ");
        sb.append("<td>Id</td>");
        sb.append("<td>Name</td>");
        sb.append("<td>Login</td>");
        sb.append("<td>Email</td>");
        sb.append("<td>Create time</td>");
        return sb.toString();
    }

    private String getCorrectAndDelete(User user, HttpServletRequest req) {
        StringBuilder sb = new StringBuilder("<form action='");
        sb.append(req.getContextPath()).append("/edit' method='GET'>");
        sb.append("<input type='submit' name='submit' value='Correct' style='float: left'>");
        sb.append("<input type='hidden' name='id' value=").append(user.getId()).append(">");
        sb.append("<input type='hidden' name='userName' value=").append(user.getName()).append(">");
        sb.append("<input type='hidden' name='login' value=").append(user.getLogin()).append(">");
        sb.append("<input type='hidden' name='email' value=").append(user.getEmail()).append(">");
        sb.append("</form>");
        sb.append("<form action='").append(req.getContextPath()).append("/user' method='POST'>");
        sb.append("<input type='submit' name='submit' value='Delete' style='float: left'>");
        sb.append("<input type='hidden' name='id' value=").append(user.getId()).append(">");
        sb.append("<input type='hidden' name='action' value= delete>");
        sb.append("</form>");
        return sb.toString();
    }

    private String getFindByIdAndCreate(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder("</table>");
        sb.append("<br><br>");
        sb.append("<form action='").append(req.getContextPath()).append("/user' method='POST'>");
        sb.append("<label for='sub'></label><input type='text' id='sub' name='id' value='0'>");
        sb.append("<input type='submit' name='submit' value='Find by id'>");
        sb.append("<input type='hidden' name='action' value= findById>");
        sb.append("</form>");
        sb.append("<form action='").append(req.getContextPath()).append("/create' method='GET'>");
        sb.append("<input type='submit' name='submit' value='Create new user' >");
        sb.append("</form>");
        return sb.toString();
    }

    private String fillTable(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("<td>").append(user.getId()).append("</td>");
        sb.append("<td>").append(user.getName()).append("</td>");
        sb.append("<td>").append(user.getLogin()).append("</td>");
        sb.append("<td>").append(user.getEmail()).append("</td>");
        sb.append("<td>").append(user.getCreateDate()).append("</td>");
        return sb.toString();
    }

    @Override
    public void destroy() {
        this.vs.close();
        super.destroy();
    }
}
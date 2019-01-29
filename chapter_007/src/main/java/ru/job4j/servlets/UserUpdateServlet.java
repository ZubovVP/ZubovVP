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
 * Date: 14.12.2018
 */
public class UserUpdateServlet extends HttpServlet {
    private final ValidateService vs = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder("<table align='left' width='60%' border='1'>");
        sb.append("<td>Name</td>");
        sb.append("<td>Login</td>");
        sb.append("<td>Email</td></tr>");
        sb.append("<form action='").append(req.getContextPath()).append("/edit' method='POST'>");
        sb.append("<td> name : <input type='text' name='name' value=").append(req.getParameter("userName")).append("></td>");
        sb.append("<td> login : <input type='text' name='login' value=").append(req.getParameter("login")).append("></td>");
        sb.append("<td> email : <input type='text' name='email' value=").append(req.getParameter("email")).append("></td>");
        sb.append("</table>");
        sb.append("<br><br><br>");
        sb.append("<input type='hidden' name='id' value=").append(req.getParameter("id")).append(">");
        sb.append("<input type='hidden' name='name' value=").append(req.getParameter("userName")).append(">");
        sb.append("<input type='hidden' name='login' value=").append(req.getParameter("login")).append(">");
        sb.append("<input type='hidden' name='email' value=").append(req.getParameter("email")).append(">");
        sb.append("<td>").append("<input type='submit' name='create' value='Update' >").append("</td>");
        sb.append("</form>");
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>");
        writer.append("<html lang='en'>");
        writer.append("<head>");
        writer.append("    <meta charset='UTF-8'>");
        writer.append("    <title></title>");
        writer.append("</head>");
        writer.append("<body>");
        writer.append(sb.toString());
        writer.append("</body>");
        writer.append("</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        try {
            this.vs.update(new User(Integer.parseInt(req.getParameter("id")), req.getParameter("name"), req.getParameter("login"), req.getParameter("email")));
        } catch (IncorrectDateException e) {
            e.getMessage();
            resp.sendError(406, e.getMessage());
        }
        writer.append("<!DOCTYPE html>");
        writer.append("<html lang='en'>");
        writer.append("<head>");
        writer.append("    <meta charset='UTF-8'>");
        writer.append("    <title>Users</title>");
        writer.append("</head>");
        writer.append("<body>");
        writer.append("User edited!");
        writer.append("<tr>");
        writer.append("<form action='").append(req.getContextPath()).append("/' method='GET'>");
        writer.append("<td>").append("<input type='submit' name='create' value='List of users' >").append("</td>");
        writer.append("</form>");
        writer.append("</body>");
        writer.append("</html>");
        writer.flush();
    }

    @Override
    public void destroy() {
        this.vs.close();
        super.destroy();
    }
}

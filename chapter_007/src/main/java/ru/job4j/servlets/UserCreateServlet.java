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
public class UserCreateServlet extends HttpServlet {
    private final ValidateService vs = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder sb = new StringBuilder("<table align='left' width='50%' border='1'>");
        sb.append("<tr><td>Name</td>");
        sb.append("<td>Login</td>");
        sb.append("<td>Email</td></tr>");
        sb.append("<form action='").append(req.getContextPath()).append("/create' method='POST'>");
        sb.append("<td> name : <input type='text' name='name' placeholder='Your name'></td>");
        sb.append("<td>login : <input type='text' name='login' placeholder='Your login'></td>");
        sb.append("<td> email : <input type='text' name='email' placeholder='Your email'></td>");
        sb.append("</table>");
        sb.append("<br><br><br>");
        sb.append("<td><input type='submit' name='create' value='Create' ></td>");
        sb.append("</form>");

        writer.append("<!DOCTYPE html>");
        writer.append("<html lang='en'>");
        writer.append("<head>");
        writer.append("    <meta charset='UTF-8'>");
        writer.append("    <title></title>");
        writer.append("</head>");
        writer.append("<body>");
        writer.append(sb);
        writer.append("</body>");
        writer.append("</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        try {
            this.vs.add(user);
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
        writer.append("User added!");
        writer.append("<tr>");
        writer.append("<form action='").append(req.getContextPath()).append("/user' method='get'>");
        writer.append("<td><input type='submit' name='create' value='List of users' ></td>");
        writer.append("</form>");
        writer.append("</body>");
        writer.append("</html>");
        writer.flush();
    }
}

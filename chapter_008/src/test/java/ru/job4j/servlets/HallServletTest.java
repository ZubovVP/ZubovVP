package ru.job4j.servlets;

import org.junit.After;
import org.junit.Test;

import ru.job4j.persistence.DBAccounts;
import ru.job4j.persistence.DBHalls;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 01.08.2019.
 */
public class HallServletTest {
    private HttpServletResponse resp;
    private HttpServletRequest req;

    @After
    public void finish() {
        DBHalls.getInstance().clearAll();
        DBAccounts.getInstance().clearAll();
    }

    @Test
    public void testDoGet() throws IOException {
        this.req = mock(HttpServletRequest.class);
        this.resp = mock(HttpServletResponse.class);
        when(this.req.getParameter("id")).thenReturn("1");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        try {
            when(resp.getWriter()).thenReturn(printWriter);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        new HallServlet().doGet(req, resp);
        printWriter.flush();
        assertTrue(stringWriter.toString().contains("\"id\":1"));
        assertTrue(stringWriter.toString().contains("\"idUser\":0"));
        assertTrue(stringWriter.toString().contains("\"row\":1"));
        assertTrue(stringWriter.toString().contains("\"seat\":1"));
        assertTrue(stringWriter.toString().contains("\"status\":\"free\""));
    }

    @Test
    public void testDoPost() throws ServletException, IOException {
        this.req = mock(HttpServletRequest.class);
        this.resp = mock(HttpServletResponse.class);
        when(this.req.getParameter("id")).thenReturn("1");
        when(this.req.getParameter("status")).thenReturn("reserve");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        try {
            when(resp.getWriter()).thenReturn(printWriter);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        new HallServlet().doPost(req, resp);
        printWriter.flush();
        assertTrue(stringWriter.toString().contains("\"id\":1"));
        assertTrue(stringWriter.toString().contains("\"idUser\":0"));
        assertTrue(stringWriter.toString().contains("\"row\":1"));
        assertTrue(stringWriter.toString().contains("\"seat\":1"));
        assertTrue(stringWriter.toString().contains("\"status\":\"reserve\""));
    }

    @Test
    public void testDoPost1() throws ServletException, IOException {
        this.req = mock(HttpServletRequest.class);
        this.resp = mock(HttpServletResponse.class);
        when(this.req.getParameter("id")).thenReturn("1");
        when(this.req.getParameter("status")).thenReturn("sold");
        when(this.req.getParameter("userName")).thenReturn("Duke");
        when(this.req.getParameter("phone")).thenReturn("123");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        try {
            when(resp.getWriter()).thenReturn(printWriter);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        new HallServlet().doPost(req, resp);
        printWriter.flush();
        assertTrue(stringWriter.toString().contains("\"id\":1"));
        assertTrue(stringWriter.toString().contains("\"row\":1"));
        assertTrue(stringWriter.toString().contains("\"seat\":1"));
        assertTrue(stringWriter.toString().contains("\"status\":\"sold\""));
    }
}
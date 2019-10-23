package ru.job4j.servlets;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Jobs;
import ru.job4j.storage.Actions;
import ru.job4j.storage.DBService;
import ru.job4j.storage.Operator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 22.10.2019.
 */
public class ServiceServletTest {
    private Jobs.Job job1 = new Jobs.Job("device1", "user1", "fax", 1);
    private Jobs.Job job2 = new Jobs.Job("device2", "user2", "print", 2);
    private DBService storage = DBService.getInstance();

    @Before
    public void start() {
        storage.add(this.job1);
        storage.add(this.job2);
    }

    @After
    public void finish() {
        DBService.getInstance().clearAll();
    }

    @Test
    public void testDoGetdAllJobs() {
        ArrayList<Jobs.Job> jobs = (ArrayList<Jobs.Job>) this.storage.getAllJob();
        assertThat(jobs.size(), is(2));
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        try (StringWriter stringWriter = new StringWriter();
             PrintWriter printWriter = new PrintWriter(stringWriter)) {
            when(resp.getWriter()).thenReturn(printWriter);
            new ServiceServlet().doGet(req, resp);
            printWriter.flush();
            for (Jobs.Job job : jobs) {
                assertTrue(stringWriter.toString().contains(String.valueOf(job.getId())));
                assertTrue(stringWriter.toString().contains(String.valueOf(job.getDevice())));
                assertTrue(stringWriter.toString().contains(String.valueOf(job.getUser())));
                assertTrue(stringWriter.toString().contains(String.valueOf(job.getType())));
                assertTrue(stringWriter.toString().contains(String.valueOf(job.getDate())));
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    @Test
    public void testDoGetdFindByUser() {
        ArrayList<Jobs.Job> jobs = (ArrayList<Jobs.Job>) this.storage.getAllJob();
        assertThat(jobs.size(), is(2));
        String result = doGetOperation(jobs.get(1).getUser(), "findByUser");
        assertTrue(result.contains(String.valueOf(jobs.get(1).getId())));
        assertTrue(result.contains(String.valueOf(jobs.get(1).getDevice())));
        assertTrue(result.contains(String.valueOf(jobs.get(1).getUser())));
        assertTrue(result.contains(String.valueOf(jobs.get(1).getType())));
    }

    @Test
    public void testDoGetFindByDevice() {
        ArrayList<Jobs.Job> jobs = (ArrayList<Jobs.Job>) this.storage.getAllJob();
        assertThat(jobs.size(), is(2));
        String result = doGetOperation(jobs.get(0).getType(), "findByType");
        assertTrue(result.contains(String.valueOf(jobs.get(0).getId())));
        assertTrue(result.contains(String.valueOf(jobs.get(0).getDevice())));
        assertTrue(result.contains(String.valueOf(jobs.get(0).getUser())));
        assertTrue(result.contains(String.valueOf(jobs.get(0).getType())));

    }

    @Test
    public void testDoGetFindByType() {
        ArrayList<Jobs.Job> jobs = (ArrayList<Jobs.Job>) this.storage.getAllJob();
        assertThat(jobs.size(), is(2));
        String result = doGetOperation(jobs.get(1).getType(), "findByType");
        assertTrue(result.contains(String.valueOf(jobs.get(1).getId())));
        assertTrue(result.contains(String.valueOf(jobs.get(1).getDevice())));
        assertTrue(result.contains(String.valueOf(jobs.get(1).getUser())));
        assertTrue(result.contains(String.valueOf(jobs.get(1).getType())));
    }

    private String doGetOperation(String name, String action) {
        String result = null;
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        try (StringWriter stringWriter = new StringWriter();
             PrintWriter printWriter = new PrintWriter(stringWriter)) {
            when(req.getParameter("name")).thenReturn(name);
            when(req.getParameter("action")).thenReturn(action);
            when(resp.getWriter()).thenReturn(printWriter);
            new ServiceServlet().doGet(req, resp);
            printWriter.flush();
            result = stringWriter.toString();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return result;
    }

    @Test
    public void testDoGetFindByDeviceSouldNull() {
        ArrayList<Jobs.Job> jobs = (ArrayList<Jobs.Job>) this.storage.getAllJob();
        assertThat(jobs.size(), is(2));
        String result = doGetOperation("Nonexistent", "findByType");
        assertThat(result, is("[]"));
    }

    @Test
    public void testDoPostAddElementInDB() {
        ArrayList<Jobs.Job> jobs = (ArrayList<Jobs.Job>) this.storage.getAllJob();
        assertThat(jobs.size(), is(2));
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        try (StringWriter stringWriter = new StringWriter();
             PrintWriter printWriter = new PrintWriter(stringWriter)) {
            when(req.getParameter("username")).thenReturn("TestName");
            when(req.getParameter("type")).thenReturn("fax");
            when(req.getParameter("device")).thenReturn("TestDevice");
            when(req.getParameter("amount")).thenReturn("2");
            when(resp.getWriter()).thenReturn(printWriter);
            new ServiceServlet().doPost(req, resp);
            printWriter.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        jobs = (ArrayList<Jobs.Job>) this.storage.getAllJob();
        assertThat(jobs.size(), is(3));
    }
}
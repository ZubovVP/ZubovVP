package ru.job4j.servlets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.models.User;
import ru.job4j.storage.Store;
import ru.job4j.storage.ValidateService;
import ru.job4j.storage.ValidateStub;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 19.03.2019.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class FindByIdServletTest {
    private HttpServletResponse resp;
    private HttpServletRequest req;

    @Test
    public void whenAddElementAndFindByIdElement() throws IOException, ServletException {
        addElement();
        String path = "WEB-INF/views/FindById.jsp";
        Store validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        this.req = mock(HttpServletRequest.class);
        this.resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(this.req.getParameter("id")).thenReturn("1");
        when(this.req.getRequestDispatcher(path)).thenReturn(dispatcher);
        new FindByIdServlet().doPost(this.req, this.resp);
        verify(this.req, times(1)).getRequestDispatcher(path);
        verify(this.req, never()).getSession();
        verify(dispatcher).forward(this.req, this.resp);
    }

    private void addElement() throws IOException {
        Store validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        this.req = mock(HttpServletRequest.class);
        this.resp = mock(HttpServletResponse.class);
        when(this.req.getParameter("name")).thenReturn("Vitaly Zubov");
        when(this.req.getParameter("role")).thenReturn("admin");
        when(this.req.getParameter("login")).thenReturn("Login");
        when(this.req.getParameter("email")).thenReturn("@root");
        when(this.req.getParameter("password")).thenReturn("123");
        when(this.req.getParameter("country")).thenReturn("country");
        when(this.req.getParameter("city")).thenReturn("city");
        new UserCreateServlet().doPost(this.req, this.resp);
        Iterator<User> itr = validate.findAll().iterator();
        assertThat(itr.next().getName(), is("Vitaly Zubov"));
    }
}
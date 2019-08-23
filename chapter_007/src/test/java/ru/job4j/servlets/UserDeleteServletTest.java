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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 19.03.2019.
 */
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*", "com.sun.org.apache.xalan.*"})
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserDeleteServletTest {
    private HttpServletResponse resp;
    private HttpServletRequest req;

    @Test
    public void whenAddUserAndDeleteUser() throws IOException {
        Store validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        addElement();
        when(ValidateService.getInstance()).thenReturn(validate);
        this.req = mock(HttpServletRequest.class);
        this.resp = mock(HttpServletResponse.class);
        when(this.req.getParameter("id")).thenReturn("1");
        new UserDeleteServlet().doPost(this.req, this.resp);
        assertThat(validate.findAll().isEmpty(), is(true));
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
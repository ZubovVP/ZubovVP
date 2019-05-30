package ru.job4j.servlets;

import org.junit.Test;
import ru.job4j.models.Person;
import ru.job4j.storage.MyStorage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 30.05.2019.
 */
public class JsonControllerTest {

    @Test
    public void sendPersonInTheStorageAndGetPerson() {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        Person personTest = new Person("Duke", "Nukem", "123", "Male", "Desc");
        Person personTest2 = new Person("Alex", "Smith", "321", "Male", "Desc");
        MyStorage storage = MyStorage.getInstance();
        ArrayList<Person> persons = storage.getAllPersons();
        assertThat(persons.size(), is(0));
        when(req.getParameter("name")).thenReturn(personTest.getName());
        when(req.getParameter("surname")).thenReturn(personTest.getSurname());
        when(req.getParameter("id")).thenReturn(personTest.getId());
        when(req.getParameter("sex")).thenReturn(personTest.getSex());
        when(req.getParameter("description")).thenReturn(personTest.getDescription());
        new JsonController().doPost(req, resp);
        persons = storage.getAllPersons();
        assertThat(persons.size(), is(1));
        assertThat(persons.get(0), is(personTest));
        when(req.getParameter("name")).thenReturn(personTest2.getName());
        when(req.getParameter("surname")).thenReturn(personTest2.getSurname());
        when(req.getParameter("id")).thenReturn(personTest2.getId());
        when(req.getParameter("sex")).thenReturn(personTest2.getSex());
        when(req.getParameter("description")).thenReturn(personTest2.getDescription());
        new JsonController().doPost(req, resp);
        persons = storage.getAllPersons();
        assertThat(persons.size(), is(2));
        assertThat(persons.get(0), is(personTest));
        assertThat(persons.get(1), is(personTest2));
    }
}
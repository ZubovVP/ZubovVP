package ru.job4j.servlets;

import org.junit.Test;
import ru.job4j.models.Admin;
import ru.job4j.models.User;
import ru.job4j.storage.DBStore;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov
 * Email: Zubov.VP@yandex.ru
 * Version: $Id$
 * Date: 03.01.2019
 */
public class DBStoreTest {
    private DBStore db;
    private User userTest = new Admin("Name", "Login", "Email", "TestPassword");

    @Test
    public void getInstance() {
        assertNull(this.db);
        this.db = DBStore.getInstance();
        assertNotNull(this.db);
    }

    @Test
    public void addElementAndCheckElement() {
        this.db = DBStore.getInstance();
        assertTrue(this.db.findAll().isEmpty());
        this.db.add(this.userTest);
        List<User> usersTest = this.db.findAll();
        assertFalse(db.findAll().isEmpty());
        assertThat(usersTest.size(), is(1));
        assertThat(usersTest.get(0).getName(), is(this.userTest.getName()));
        assertThat(usersTest.get(0).getLogin(), is(this.userTest.getLogin()));
        assertThat(usersTest.get(0).getEmail(), is(this.userTest.getEmail()));
        this.db.delete(usersTest.get(0).getId());
    }

    @Test
    public void addElementUpdateElementAndCheckElement() {
        this.db = DBStore.getInstance();
        assertTrue(this.db.findAll().isEmpty());
        this.db.add(this.userTest);
        User userTest2 = this.db.findAll().get(0);
        userTest2.setName("NameTest2");
        userTest2.setLogin("LoginTest2");
        userTest2.setEmail("EmailTest2");
        this.db.update(userTest2);
        List<User> users = this.db.findAll();
        assertThat(users.size(), is(1));
        assertThat(users.get(0).getName(), is(userTest2.getName()));
        assertThat(users.get(0).getLogin(), is(userTest2.getLogin()));
        assertThat(users.get(0).getEmail(), is(userTest2.getEmail()));
        this.db.delete(userTest2.getId());
    }

    @Test
    public void addElementCheckDeleteElementCheck() {
        this.db = DBStore.getInstance();
        assertTrue(db.findAll().isEmpty());
        this.db.add(this.userTest);
        User userTest2 = this.db.findAll().get(0);
        assertFalse(db.findAll().isEmpty());
        this.db.delete(userTest2.getId());
        assertTrue(db.findAll().isEmpty());
    }

    @Test
    public void findAll() {
        this.db = DBStore.getInstance();
        assertTrue(this.db.findAll().isEmpty());
        this.db.add(this.userTest);
        assertThat(this.db.findAll().size(), is(1));
        this.db.add(new Admin("NameTest2", "LoginTest2", "EmailTest2", "TestPassword"));
        assertThat(this.db.findAll().size(), is(2));
        for (User us : this.db.findAll()) {
            this.db.delete(us.getId());
        }
    }

    @Test
    public void findById() {
        this.db = DBStore.getInstance();
        assertTrue(this.db.findAll().isEmpty());
        this.db.add(this.userTest);
        User user2 = this.db.findAll().get(0);
        assertThat(this.db.findById(user2.getId()), is(user2));
        this.db.delete(user2.getId());
    }

    @Test
    public void findByLogin() {
        this.db = DBStore.getInstance();
        assertTrue(this.db.findAll().isEmpty());
        this.db.add(this.userTest);
        User user2 = this.db.findAll().get(0);
        assertThat(this.db.findByLogin(user2.getLogin()), is(user2));
        this.db.delete(user2.getId());
    }
}
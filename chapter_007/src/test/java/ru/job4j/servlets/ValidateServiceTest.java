package ru.job4j.servlets;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Admin;
import ru.job4j.models.User;
import ru.job4j.storage.*;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov
 * Email: Zubov.VP@yandex.ru
 * Version: $Id$
 * Date: 26.12.2018
 */
public class ValidateServiceTest {
    private Store vs;
    private Store<User> db = DBStore.getInstance();
    private User userTest = new Admin("NameTest", "LoginTest", "EmailTest", "TestPassword");

    @Before
    public void start() {
        this.vs = ValidateService.getInstance();
        assertNotNull(this.vs);
    }

    @After
    public void finish() {
        checkDB();
    }

    private void checkDB() {
        List<User> users = this.db.findAll();
        for (User user : users) {
            this.db.delete(user.getId());
        }
    }

    @Test(expected = IncorrectDateException.class)
    public void addTheSameElement() {
        checkDB();
        this.vs.add(this.userTest);
        this.vs.add(this.userTest);
    }

    @Test(expected = IncorrectDateException.class)
    public void addClearElement() {
        checkDB();
        User userTest = new Admin("", "", "", "");
        this.vs.add(userTest);
    }

    @Test(expected = IncorrectDateException.class)
    public void addElementWithClearName() {
        checkDB();
        User userTest = new Admin("", "LoginTest", "EmailTest", "TestPassword");
        this.vs.add(userTest);
    }

    @Test(expected = IncorrectDateException.class)
    public void addElementWithClearLogin() {
        checkDB();
        User userTest = new Admin("NameTest", "", "EmailTest", "TestPassword");
        this.vs.add(userTest);
    }

    @Test(expected = IncorrectDateException.class)
    public void addElementWithClearEmail() {
        checkDB();
        User userTest = new Admin("NameTest", "LoginTest", "", "TestPassword");
        this.vs.add(userTest);
    }

    @Test(expected = IncorrectDateException.class)
    public void addElementWithClearPassword() {
        checkDB();
        User userTest = new Admin("NameTest", "LoginTest", "EmailTest", "");
        this.vs.add(userTest);
    }

    @Test(expected = IncorrectDateException.class)
    public void updateNotExistingElement() {
        this.vs.update(this.userTest);
    }

    @Test(expected = IncorrectDateException.class)
    public void updateElementWhithExistEmail() {
        checkDB();
        User userTest = new Admin("NameTest", "LoginTest", "EmailTest", "TestPassword");
        this.vs.add(userTest);
        User userTest2 = new Admin("NameTest1", "LoginTest1", "EmailTest1", "TestPassword");
        this.vs.add(userTest2);
        userTest2 = (User) this.vs.findAll().get(1);
        userTest2.setEmail("EmailTest");
        this.vs.update(userTest2);
    }

    @Test(expected = IncorrectDateException.class)
    public void updateElementWithExistLogin() {
        checkDB();
        User userTest = new Admin("NameTest", "LoginTest", "EmailTest", "TestPassword");
        this.vs.add(userTest);
        User userTest2 = new Admin("NameTest1", "LoginTest1", "EmailTest1", "TestPassword");
        this.vs.add(userTest2);
        userTest2 = (User) this.vs.findAll().get(1);
        userTest2.setLogin("LoginTest");
        this.vs.update(userTest2);
    }

    @Test(expected = IncorrectDateException.class)
    public void updateElementWithBusyLogin() {
        checkDB();
        this.vs.add(this.userTest);
        User user2 = new Admin("NameTest", "LoginTest1", "EmailTest1", "TestPassword");
        this.vs.add(user2);
        user2 = (User) this.vs.findAll().get(1);
        user2.setLogin("LoginTest");
        this.vs.update(user2);
    }

    @Test(expected = IncorrectDateException.class)
    public void updateElementWithBusyEmail() {
        checkDB();
        this.vs.add(this.userTest);
        User user2 = new Admin("NameTest", "LoginTest1", "EmailTest1", "TestPassword");
        this.vs.add(user2);
        user2 = (User) this.vs.findAll().get(1);
        user2.setEmail("EmailTest");
        this.vs.update(user2);
    }

    @Test(expected = IncorrectDateException.class)
    public void findByIdWhereIdNotExist() {
        this.vs.findById(0);
    }

    @Test
    public void deleteElement() {
        checkDB();
        this.vs.add(this.userTest);
        User user2 = (User) this.vs.findAll().get(0);
        this.vs.delete(user2.getId());
        Assert.assertThat(0, is(this.vs.findAll().size()));
    }
}
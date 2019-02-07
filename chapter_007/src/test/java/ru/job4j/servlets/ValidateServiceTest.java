package ru.job4j.servlets;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.User;
import ru.job4j.storage.DBStore;
import ru.job4j.storage.IncorrectDateException;
import ru.job4j.storage.ValidateService;

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
    private ValidateService vs;
    private DBStore db = DBStore.getInstance();
    private User userTest = new User("NameTest", "LoginTest", "EmailTest");

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
        User userTest = new User("", "", "");
        this.vs.add(userTest);
    }

    @Test(expected = IncorrectDateException.class)
    public void addElementWithClearName() {
        checkDB();
        User userTest = new User("", "LoginTest", "EmailTest");
        this.vs.add(userTest);
    }

    @Test(expected = IncorrectDateException.class)
    public void addElementWithClearLogin() {
        checkDB();
        User userTest = new User("NameTest", "", "EmailTest");
        this.vs.add(userTest);
    }

    @Test(expected = IncorrectDateException.class)
    public void addElementWithClearEmail() {
        checkDB();
        User userTest = new User("NameTest", "LoginTest", "");
        this.vs.add(userTest);
    }

    @Test(expected = IncorrectDateException.class)
    public void updateNotExistingElement() {
        this.vs.update(this.userTest);
    }

    @Test(expected = IncorrectDateException.class)
    public void updateElementWithBusyLogin() {
        checkDB();
        this.vs.add(this.userTest);
        User user2 = new User("NameTest", "LoginTest1", "EmailTest1");
        this.vs.add(user2);
        user2 = this.vs.findAll().get(1);
        user2.setLogin("LoginTest");
        this.vs.update(user2);
    }

    @Test(expected = IncorrectDateException.class)
    public void updateElementWithBusyEmail() {
        checkDB();
        this.vs.add(this.userTest);
        User user2 = new User("NameTest", "LoginTest1", "EmailTest1");
        this.vs.add(user2);
        user2 = this.vs.findAll().get(1);
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
        User user2 = this.vs.findAll().get(0);
        this.vs.delete(user2.getId());
        Assert.assertThat(0, is(this.vs.findAll().size()));
    }
}
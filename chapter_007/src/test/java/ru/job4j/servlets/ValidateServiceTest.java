package ru.job4j.servlets;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov
 * Email: Zubov.VP@yandex.ru
 * Version: $Id$
 * Date: 26.12.2018
 */
public class ValidateServiceTest {
    private ValidateService vs;
    private User userTest = new User("NameTest", "LoginTest", "EmailTest");


    @Before
    public void start() {
        this.vs = ValidateService.getInstance();
        assertFalse(this.vs == null);
    }

    @Test(expected = IncorrectDateException.class)
    public void addTheSameElement() {
        this.vs.add(this.userTest);
        this.vs.add(this.userTest);
    }

    @Test(expected = IncorrectDateException.class)
    public void addClearElement() {
        User userTest = new User("", "", "");
        this.vs.add(userTest);
    }

    @Test(expected = IncorrectDateException.class)
    public void addElementWhithClearName() {
        User userTest = new User("", "LoginTest", "EmailTest");
        this.vs.add(userTest);
    }

    @Test(expected = IncorrectDateException.class)
    public void addElementWhithClearLogin() {
        User userTest = new User("NameTest", "", "EmailTest");
        this.vs.add(userTest);
    }

    @Test(expected = IncorrectDateException.class)
    public void addElementWhithClearEmail() {
        User userTest = new User("NameTest", "LoginTest", "");
        this.vs.add(userTest);
    }

    @Test(expected = IncorrectDateException.class)
    public void updateNotExistingElement() {
        this.vs.update(this.userTest);
    }

    @Test(expected = IncorrectDateException.class)
    public void updateElementWithBusyLogin() {
        this.vs.add(this.userTest);
        User user2 = new User("NameTest", "LoginTest1", "EmailTest1");
        this.vs.add(user2);
        user2 = this.vs.findAll().get(1);
        user2.setLogin("LoginTest");
        this.vs.update(user2);
    }

    @Test(expected = IncorrectDateException.class)
    public void updateElementWithBusyEmail() {
        this.vs.add(this.userTest);
        User user2 = new User("NameTest", "LoginTest1", "EmailTest1");
        this.vs.add(user2);
        user2 = this.vs.findAll().get(1);
        user2.setEmail("EmailTest");
        this.vs.update(user2);
    }

    @Test(expected = IncorrectDateException.class)
    public void deleteNotExistingElement() {
        User userTest = new User("NameTest", "LoginTest", "EmailTest");
        this.vs.update(userTest);
    }

    @Test(expected = IncorrectDateException.class)
    public void findByIdWhereIdNotExist() {
        this.vs.findById(10);
    }
}
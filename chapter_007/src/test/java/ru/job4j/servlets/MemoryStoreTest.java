package ru.job4j.servlets;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Admin;
import ru.job4j.models.User;
import ru.job4j.storage.MemoryStore;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov
 * Email: Zubov.VP@yandex.ru
 * Version: $Id$
 * Date: 26.12.2018
 */
public class MemoryStoreTest {
    private MemoryStore memoryTest;
    private User userTest1 = new Admin("TestName1", "TestLogin1", "TestEmail1", "TestPassword", "Country", "City");
    private User userTest2 = new Admin("TestName2", "TestLogin2", "TestEmail2", "TestPassword", "Country", "City");


    @Before
    public void getInstanceTest() {
        this.memoryTest = MemoryStore.getInstance();
        assertNotNull(this.memoryTest);
    }

    @Test
    public void addElement() {
        boolean result = false;
        assertTrue(this.memoryTest.findAll().isEmpty());
        this.memoryTest.add(this.userTest1);
        for (User user : this.memoryTest.findAll()) {
            if (user.getName().equals(this.userTest1.getName()) && user.getLogin().equals(this.userTest1.getLogin()) && user.getEmail().equals(this.userTest1.getEmail())) {
                result = true;
                this.memoryTest.delete(user.getId());
                break;
            }
        }
        assertTrue(result);
    }

    @Test
    public void updateTheElement() {
        boolean result = false;
        this.memoryTest.add(this.userTest1);
        for (User user : this.memoryTest.findAll()) {
            if (user.getName().equals(this.userTest1.getName()) && user.getLogin().equals(this.userTest1.getLogin()) && user.getEmail().equals(this.userTest1.getEmail())) {
                result = true;
                break;
            }
        }
        assertTrue(result);
        result = false;
        User userTestNew = this.memoryTest.findAll().get(0);
        userTestNew.setName("TestName1");
        userTestNew.setLogin("TestLogin1");
        userTestNew.setEmail("TestEmail1");
        this.memoryTest.update(userTestNew);
        for (User user : this.memoryTest.findAll()) {
            if (user.getName().equals(userTestNew.getName()) && user.getLogin().equals(userTestNew.getLogin()) && user.getEmail().equals(userTestNew.getEmail())) {
                result = true;
                this.memoryTest.delete(user.getId());
                break;
            }
        }
        assertTrue(result);
    }

    @Test
    public void deleteTheElement() {
        this.memoryTest.add(this.userTest1);
        assertFalse(this.memoryTest.findAll().isEmpty());
        this.memoryTest.delete(this.memoryTest.findAll().get(0).getId());
        assertTrue(this.memoryTest.findAll().isEmpty());
    }

    @Test
    public void findAllElements() {
        this.memoryTest.add(this.userTest1);
        this.memoryTest.add(this.userTest2);
        assertThat(this.memoryTest.findAll().size(), is(2));
        for (User user : this.memoryTest.findAll()) {
            this.memoryTest.delete(user.getId());
        }
    }

    @Test
    public void findById() {
        this.memoryTest.add(this.userTest1);
        this.memoryTest.add(this.userTest2);
        for (User user : this.memoryTest.findAll()) {
            if (user.getName().equals(this.userTest1.getName()) && user.getLogin().equals(this.userTest1.getLogin()) && user.getEmail().equals(this.userTest1.getEmail())) {
                userTest1.setId(user.getId());
            }
            if (user.getName().equals(userTest2.getName()) && user.getLogin().equals(userTest2.getLogin()) && user.getEmail().equals(userTest2.getEmail())) {
                userTest2.setId(user.getId());
            }
        }
        assertEquals(this.memoryTest.findById(userTest1.getId()).getName(), userTest1.getName());
        assertEquals(this.memoryTest.findById(userTest1.getId()).getLogin(), userTest1.getLogin());
        assertEquals(this.memoryTest.findById(userTest2.getId()).getName(), userTest2.getName());
        assertEquals(this.memoryTest.findById(userTest2.getId()).getLogin(), userTest2.getLogin());
        this.memoryTest.delete(userTest1.getId());
        this.memoryTest.delete(userTest2.getId());
    }

    @Test
    public void findUserByTheLogin() {
        this.memoryTest.add(this.userTest1);
        this.memoryTest.add(this.userTest2);
        for (User user : this.memoryTest.findAll()) {
            if (user.getName().equals(this.userTest1.getName()) && user.getLogin().equals(this.userTest1.getLogin()) && user.getEmail().equals(this.userTest1.getEmail())) {
                userTest1.setId(user.getId());
            }
            if (user.getName().equals(userTest2.getName()) && user.getLogin().equals(userTest2.getLogin()) && user.getEmail().equals(userTest2.getEmail())) {
                userTest2.setId(user.getId());
            }
        }
        assertEquals(this.memoryTest.findByLogin(userTest1.getLogin()).getName(), userTest1.getName());
        assertEquals(this.memoryTest.findByLogin(userTest1.getLogin()).getLogin(), userTest1.getLogin());
        assertEquals(this.memoryTest.findByLogin(userTest2.getLogin()).getName(), userTest2.getName());
        assertEquals(this.memoryTest.findByLogin(userTest2.getLogin()).getLogin(), userTest2.getLogin());
        this.memoryTest.delete(userTest1.getId());
        this.memoryTest.delete(userTest2.getId());
    }
}
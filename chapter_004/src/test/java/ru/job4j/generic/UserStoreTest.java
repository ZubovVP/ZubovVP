package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class UserStoreTest {
    private User userTest;
    private UserStore userStoreTest = new UserStore();
    private Base result;

    @Before
    public void start() {
        userTest = new User("1");
        userStoreTest.add(userTest);
        userStoreTest.add(new User("2"));
    }

    @Test
    public void whenNeedAdd() throws Exception {
        userTest = new User("3");
        userStoreTest.add(userTest);
        result = userStoreTest.findById(userTest.getId());
        assertThat(result.getId(), is(userTest.getId()));
    }

    @Test
    public void whenNeedReplace() throws Exception {
        Role replace = new Role("3");
        userStoreTest.replace(userTest.getId(), replace);
        result = userStoreTest.findById(replace.getId());
        assertThat(result.getId(), is(replace.getId()));

        result = null;
        result = userStoreTest.findById(userTest.getId());
        assertTrue(result == null);
    }

    @Test
    public void whenNeedDelete() throws Exception {
        Boolean condition = userStoreTest.delete(userTest.getId());
        assertTrue(condition);

        condition = userStoreTest.delete(userTest.getId());
        assertTrue(condition);
    }

    @Test
    public void whenNeedFindById() throws Exception {
        result = userStoreTest.findById(userTest.getId());
        assertThat(result.getId(), is(userTest.getId()));

        result = null;
        result = userStoreTest.findById(new Role("55").getId());
        assertTrue(result == null);

    }
}

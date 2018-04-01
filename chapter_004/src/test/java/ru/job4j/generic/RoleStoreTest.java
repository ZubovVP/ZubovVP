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
public class RoleStoreTest {
    private Role roleTest;
    private RoleStore roleStoreTest = new RoleStore();
    private Base result;

    @Before
    public void start() {
        roleTest = new Role("1");
        roleStoreTest.add(roleTest);
        roleStoreTest.add(new Role("2"));
    }

    @Test
    public void whenNeedAdd()throws Exception {
        roleTest = new Role("3");
        roleStoreTest.add(roleTest);
        result = roleStoreTest.findById(roleTest.getId());
        assertThat(result.getId(), is(roleTest.getId()));
    }

    @Test
    public void whenNeedReplace()throws Exception {
        Role replace = new Role("3");
        roleStoreTest.replace(roleTest.getId(), replace);
        result = roleStoreTest.findById(replace.getId());
        assertThat(result.getId(), is(replace.getId()));

        result = null;
        result = roleStoreTest.findById(roleTest.getId());
        assertTrue(result == null);
    }

    @Test
    public void whenNeedDelete() throws Exception {
        Boolean condition = roleStoreTest.delete(roleTest.getId());
        assertTrue(condition);

        condition = roleStoreTest.delete(roleTest.getId());
        assertTrue(condition);
    }

    @Test
    public void whenNeedFindById() throws Exception {
        result =  roleStoreTest.findById(roleTest.getId());
        assertThat(result.getId(), is(roleTest.getId()));

        result = null;
        result = roleStoreTest.findById(new Role("55").getId());
        assertTrue(result == null);
    }
}

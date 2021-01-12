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
public class SimpleArrayTest {
    private SimpleArray<Integer> simpleArrayIntegerTest = new SimpleArray(10);

    @Before
    public void start() {
        simpleArrayIntegerTest.add(1);
    }

    @Test
    public void add() {
        simpleArrayIntegerTest.add(2);
        assertThat(simpleArrayIntegerTest.get(0), is(1));
        assertThat(simpleArrayIntegerTest.get(1), is(2));
    }

    @Test
    public void set() {
        assertThat(simpleArrayIntegerTest.get(0), is(1));
        simpleArrayIntegerTest.set(0, 2);
        assertThat(simpleArrayIntegerTest.get(0), is(2));
    }

    @Test
    public void delete() {
        assertThat(simpleArrayIntegerTest.get(0), is(1));
        simpleArrayIntegerTest.add(2);
        simpleArrayIntegerTest.add(3);
        simpleArrayIntegerTest.delete(1);
        assertThat(simpleArrayIntegerTest.get(0), is(1));
        assertThat(simpleArrayIntegerTest.get(1), is(3));
    }

    @Test
    public void get() {
        assertThat(simpleArrayIntegerTest.get(0), is(1));
    }

    @Test
    public void hasNext() {
        assertTrue(simpleArrayIntegerTest.hasNext());
        simpleArrayIntegerTest.next();
        assertFalse(simpleArrayIntegerTest.hasNext());
    }

    @Test
    public void next() {
        simpleArrayIntegerTest.add(2);
        assertThat(simpleArrayIntegerTest.next(), is(1));
        assertThat(simpleArrayIntegerTest.next(), is(2));
    }
}
package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Add test.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class SimpleSetTest {
    private SimpleSet<String> set = new SimpleSet<>();
    private String a = "a";
    private String b = "b";

    @Before
    public void start() {
        set.add(a);
        set.add(b);
    }

    @Test
    public void addElementNotDuplicate() throws Exception {
        String c = "c";
        set.add(c);
        Iterator<String> setIterator = set.iterator();
        setIterator.next();
        setIterator.next();
        assertThat(setIterator.next(), is(c));
    }

    @Test(expected = NoSuchElementException.class)
    public void addElementDuplicate() throws Exception {
        set.add(a);
        set.add(a);
        Iterator<String> setIterator = set.iterator();
        assertThat(setIterator.next(), is(a));
        assertThat(setIterator.next(), is(b));
        setIterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void nextTest() throws Exception {
        Iterator<String> setIterator = set.iterator();
        assertThat(setIterator.next(), is(a));
        assertThat(setIterator.next(), is(b));
        setIterator.next();
    }

    @Test
    public void hasNextTest() throws Exception {
        Iterator<String> setIterator = set.iterator();
        assertTrue(setIterator.hasNext());
        assertThat(setIterator.next(), is(a));
        assertTrue(setIterator.hasNext());
        assertThat(setIterator.next(), is(b));
        assertFalse(setIterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void correctElementsInSet() throws Exception {
        Iterator<String> setIterator = set.iterator();
        set.add(a);
        assertThat(setIterator.next(), is(a));
        assertThat(setIterator.next(), is(b));
        set.add("w");
        setIterator.next();
    }
}
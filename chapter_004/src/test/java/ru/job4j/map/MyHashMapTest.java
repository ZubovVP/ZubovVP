package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class MyHashMapTest {
    private MyHashMap<Integer, String> myHashMapTest = new MyHashMap<>();

    @Before
    public void start() {
        myHashMapTest.insert(1, "Duke");
        myHashMapTest.insert(2, "Tom");
    }
    @Test
    public void insert() throws Exception {
        assertTrue(myHashMapTest.insert(3, "Kate"));
        assertFalse(myHashMapTest.insert(3, "Kate"));
    }

    @Test(expected = NoSuchElementException.class)
    public void weTestMethodGetAndGetNullElement() throws Exception {
        assertThat(myHashMapTest.get(1), is("Duke"));
        myHashMapTest.get(9);
    }

    @Test(expected = NoSuchElementException.class)
    public void weTestMethodGet() throws Exception {
        assertThat(myHashMapTest.get(2), is("Tom"));
        myHashMapTest.get(999);
    }

    @Test(expected = NoSuchElementException.class)
    public void delete() throws Exception {
        assertThat(myHashMapTest.get(1), is("Duke"));
        assertTrue(myHashMapTest.delete(1));
        myHashMapTest.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void weTestHashNextAndNext() {
        Iterator<String> itrTest = myHashMapTest.iterator();
        assertTrue(itrTest.hasNext());
        assertThat(itrTest.next(), is("Duke"));
        assertTrue(itrTest.hasNext());
        assertThat(itrTest.next(), is("Tom"));
        assertFalse(itrTest.hasNext());
        itrTest.next();
    }
}
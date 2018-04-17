package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class MyHashSetTest {
    MyHashSet<String> setTest = new MyHashSet<>();

    @Before
    public void start() {
        setTest.add("Alex");
        setTest.add("Tom");
    }

    @Test
    public void contains() throws Exception {
        assertTrue(setTest.contains("Alex"));
        assertFalse(setTest.contains("Kate"));
    }

    @Test
    public void addDublicateAndNotDublicateElement() throws Exception {
        assertFalse(setTest.add("Alex"));
        assertTrue(setTest.add("Mike"));
    }

    @Test
    public void remove() throws Exception {
        assertTrue(setTest.remove("Alex"));
        assertFalse(setTest.contains("Alex"));
        assertFalse(setTest.remove("Ann"));
        assertFalse(setTest.contains("Ann"));
    }

    @Test(expected = NoSuchElementException.class)
    public void nextAndHasNext() {
        Iterator<String> itr = setTest.iterator();
        assertTrue(itr.hasNext());
        itr.next();
        assertTrue(itr.hasNext());
        itr.next();
        assertFalse(itr.hasNext());
        itr.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testConcurrentModificationException() {
        Iterator<String> itr = setTest.iterator();
        setTest.remove("Alex");
        itr.hasNext();
    }
}
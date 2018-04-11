package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * The Tests
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class MyLinkedListTest {
    private String stringTest = "Test";
    private MyLinkedList<String> linkedListTest = new MyLinkedList<>();
    private Iterator<String> iterTest;

    @Before
    public void start() {
        linkedListTest.add(stringTest);
        linkedListTest.add(stringTest);
        iterTest = linkedListTest.iterator();
    }

    @Test
    public void whenAddObjectinMyLinkedList() {
        linkedListTest.add("a");
        assertThat(linkedListTest.get(2), is("a"));
    }

    @Test (expected = NullPointerException.class)
    public void whenRemoveObjectFromMyLinkedList() {
        linkedListTest.add("a");
        assertThat(linkedListTest.get(2), is("a"));
        linkedListTest.remove(2);
        linkedListTest.get(2);
    }

    @Test (expected = ConcurrentModificationException.class)
    public void testIterator() {
        assertTrue(iterTest.hasNext());
        assertThat(iterTest.next(), is(stringTest));
        assertTrue(iterTest.hasNext());
        linkedListTest.add(stringTest);
        iterTest.next();
    }

}
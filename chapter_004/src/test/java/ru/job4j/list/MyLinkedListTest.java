package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * The Tests
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class MyLinkedListTest {
    private String stringTest1 = "Test1";
    private String stringTest2 = "Test2";
    private MyLinkedList<String> linkedListTest = new MyLinkedList<>();
    private Iterator<String> iterTest;

    @Before
    public void start() {
        linkedListTest.add(stringTest1);
        linkedListTest.add(stringTest2);
        iterTest = linkedListTest.iterator();
    }

    @Test
    public void whenAddObjectinMyLinkedList() {
        linkedListTest.add("a");
        assertThat(linkedListTest.get(2), is("a"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveObjectFromMyLinkedList() {
        linkedListTest.add("a");
        assertThat(linkedListTest.get(2), is("a"));
        linkedListTest.remove(2);
        linkedListTest.get(2);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testIterator() {
        assertTrue(iterTest.hasNext());
        assertThat(iterTest.next(), is(stringTest1));
        assertTrue(iterTest.hasNext());
        linkedListTest.add(stringTest2);
        iterTest.next();
    }

    @Test
    public void whenAddElementUseIndexMyLinkedListInAHead() {
        linkedListTest.add(0, "a");
        assertThat(linkedListTest.get(0), is("a"));
        assertThat(linkedListTest.get(1), is(stringTest1));
        assertThat(linkedListTest.get(2), is(stringTest2));
    }

    @Test
    public void whenAddElementUseIndexMyLinkedListInMiddle() {
        linkedListTest.add(1, "a");
        assertThat(linkedListTest.get(0), is(stringTest1));
        assertThat(linkedListTest.get(1), is("a"));
        assertThat(linkedListTest.get(2), is(stringTest2));
    }

    @Test
    public void whenGetElementUseIndexMyLinkedListInMiddle() {
        assertThat(linkedListTest.get(0), is(stringTest1));
        assertThat(linkedListTest.get(1), is(stringTest2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetElementUseUnExpectedIndexMyLinkedListInMiddle() {
        assertThat(linkedListTest.get(0), is(stringTest1));
        linkedListTest.get(10);
    }

}
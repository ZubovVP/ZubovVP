package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * The Tests
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class MyArrayListTest {
   private String stringTest = "Test";
   private MyArrayList<String> arrayListTest = new MyArrayList<>();
   private Iterator<String> iterTest;


    @Before
    public void start() {
        arrayListTest.add(stringTest);
        iterTest = arrayListTest.iterator();
    }

    @Test
    public void addInMyArrayList() throws Exception {
        assertThat(arrayListTest.get(0), is(stringTest));
    }

    @Test
    public void getFromMyArrayList() throws Exception {
        assertThat(arrayListTest.get(0), is(stringTest));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void hasNextTestExceptionAndConcurrentModificationException() throws Exception {
        assertTrue(iterTest.hasNext());
        assertThat(iterTest.next(), is(stringTest));
        assertFalse(iterTest.hasNext());
        arrayListTest.add(stringTest);
        iterTest.hasNext();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenWeDoNoSuchElementException() throws Exception {
        assertThat(iterTest.next(), is(stringTest));
        iterTest.next();
    }

    @Test
    public void whenWeDoToEnsureCapacity() throws Exception {
        assertThat(arrayListTest.size(), is(1));
        arrayListTest.add(stringTest);
        arrayListTest.add(stringTest);
        arrayListTest.add(stringTest);
        arrayListTest.add(stringTest);
        arrayListTest.add(stringTest);
        arrayListTest.add(stringTest);
        arrayListTest.add(stringTest);
        arrayListTest.add(stringTest);
        arrayListTest.add(stringTest);
        arrayListTest.add(stringTest);
        arrayListTest.add(stringTest);
        assertThat(arrayListTest.size(), is(12));
    }
}
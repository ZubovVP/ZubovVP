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
 *
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
    public void addInMyArrayList() {
        assertThat(arrayListTest.get(0), is(stringTest));
    }

    @Test
    public void getFromMyArrayList() {
        assertThat(arrayListTest.get(0), is(stringTest));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void hasNextTestExceptionAndConcurrentModificationException() {
        assertTrue(iterTest.hasNext());
        assertThat(iterTest.next(), is(stringTest));
        assertFalse(iterTest.hasNext());
        arrayListTest.add(stringTest);
        iterTest.hasNext();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenWeDoNoSuchElementException() {
        assertThat(iterTest.next(), is(stringTest));
        iterTest.next();
    }

    @Test
    public void whenWeDoToEnsureCapacity() {
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

    @Test
    public void whenAddThenGet() {
        MyArrayList<String> array = new MyArrayList<>();
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        MyArrayList<String> array = new MyArrayList<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        MyArrayList<String> array = new MyArrayList<>();
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        MyArrayList<String> array = new MyArrayList<>();
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        MyArrayList<String> array = new MyArrayList<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        MyArrayList<String> array = new MyArrayList<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }

    @Test
    public void whenDeleteElementFromList() {
        MyArrayList<String> array = new MyArrayList<>();
        array.add("first");
        array.add("second");
        array.remove(0);
        assertThat(array.size(), is(1));
        assertThat(array.get(0), is("second"));
    }

    @Test
    public void whenAddElementUseIndex() {
        MyArrayList<String> array = new MyArrayList<>();
        array.add("second");
        array.add("third");
        array.add(0, "first");
        assertThat(array.size(), is(3));
        assertThat(array.get(0), is("first"));
    }

    @Test
    public void whenSetElementFromList() {
        MyArrayList<String> array = new MyArrayList<>();
        array.add("third");
        array.add("second");
        array.set(0, "first");
        assertThat(array.size(), is(2));
        assertThat(array.get(0), is("first"));
    }


}
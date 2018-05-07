package ru.job4j.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Add tests.
 *
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class MyTreeTest {
    private MyTree<Integer> myTreeTest = new MyTree<>(5);

    @Before
    public void start() {
        myTreeTest.add(2);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddElementAndCheckElementAndCheckConcurrentModificationException() throws Exception {
        myTreeTest.add(3);
        Iterator<Integer> itr = myTreeTest.iterator();
        assertThat(itr.next(), is(5));
        assertThat(itr.next(), is(2));
        assertThat(itr.next(), is(3));
        myTreeTest.add(4);
        itr.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenCheckNextAndHasNextOfTheIterator() throws Exception {
        Iterator<Integer> itr = myTreeTest.iterator();
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(5));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(2));
        assertThat(itr.hasNext(), is(false));
        itr.next();
    }
}
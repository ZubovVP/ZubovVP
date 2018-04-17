package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class SimpleLinkedSetTest {
    private SimpleLinkedSet<Integer> set = new SimpleLinkedSet<>();

    @Before
    public void start() {
        set.add(1);
        set.add(2);
    }

    @Test(expected = NullPointerException.class)
    public void addDuplicateInSimpleLinkedSet() throws Exception {
        set.add(3);
        set.add(3);
        set.add(3);
        Iterator<Integer> itr = set.iterator();
        assertThat(itr.next(), is(1));
        assertThat(itr.next(), is(2));
        assertThat(itr.next(), is(3));
        itr.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testHasNextAndNext() throws Exception {
        Iterator<Integer> itr = set.iterator();
        assertTrue(itr.hasNext());
        assertThat(itr.next(), is(1));
        assertTrue(itr.hasNext());
        assertThat(itr.next(), is(2));
        assertFalse(itr.hasNext());
        set.add(5);
        itr.next();
    }

}
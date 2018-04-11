package ru.job4j.list;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * The Tests
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class MyQueueTest {
    private MyQueue<Integer> myQueueTest = new MyQueue<>();

    @Before
    public void start() {
        myQueueTest.push(1);
        myQueueTest.push(2);
        myQueueTest.push(3);
    }

    @Test
    public void whenWePeekElement() {
        assertThat(myQueueTest.peek(), is(1));
        assertThat(myQueueTest.peek(), is(1));
    }

    @Test(expected = NullPointerException.class)
    public void whenWePoolElement() {
        assertThat(myQueueTest.poll(), is(1));
        assertThat(myQueueTest.poll(), is(2));
        assertThat(myQueueTest.poll(), is(3));
        myQueueTest.poll();
    }
}
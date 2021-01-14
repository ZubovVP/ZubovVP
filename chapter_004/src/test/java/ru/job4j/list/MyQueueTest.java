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

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenWePoolElement() {
        assertThat(myQueueTest.poll(), is(1));
        assertThat(myQueueTest.poll(), is(2));
        assertThat(myQueueTest.poll(), is(3));
        myQueueTest.poll();
    }

    @Test
    public void whenPushPoll() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.push(1);
        int rsl = queue.poll();
        assertThat(rsl, is(1));
    }

    @Test
    public void when2PushPoll() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.push(1);
        queue.push(2);
        int rsl = queue.poll();
        assertThat(rsl, is(1));
    }

    @Test
    public void when2PushPollPushPoll() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.push(1);
        queue.poll();
        queue.push(2);
        int rsl = queue.poll();
        assertThat(rsl, is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenEmptyPoll() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.poll();
    }
    @Test
    public void whenPushPushPollAndPush() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.push(1);
        queue.push(2);
        queue.poll();
        queue.push(3);
        assertThat(queue.poll(), is(2));
    }
}
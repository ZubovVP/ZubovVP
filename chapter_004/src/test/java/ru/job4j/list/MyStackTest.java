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
public class MyStackTest {
    MyStack<Integer> myStackTest = new MyStack<>();

    @Before
    public void start() {
        myStackTest.push(1);
        myStackTest.push(2);
        myStackTest.push(3);
    }

    @Test
    public void whenWePeekElement() {
        assertThat(myStackTest.peek(), is(3));
        assertThat(myStackTest.peek(), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenWePoolElement() {
        assertThat(myStackTest.poll(), is(3));
        assertThat(myStackTest.poll(), is(2));
        assertThat(myStackTest.poll(), is(1));
        myStackTest.poll();
    }

    @Test
    public void whenPushThenPoll() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        assertThat(stack.peek(), is(1));
    }

    @Test
    public void whenPushPollThenPushPoll() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.poll();
        stack.push(2);
        assertThat(stack.peek(), is(2));
    }

    @Test
    public void whenPushPushThenPollPoll() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.poll();
        assertThat(stack.peek(), is(1));
    }
}
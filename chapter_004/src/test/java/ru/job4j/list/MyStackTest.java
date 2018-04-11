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

    @Test(expected = NullPointerException.class)
    public void whenWePoolElement() {
        assertThat(myStackTest.poll(), is(3));
        assertThat(myStackTest.poll(), is(2));
        assertThat(myStackTest.poll(), is(1));
        myStackTest.poll();
    }
}
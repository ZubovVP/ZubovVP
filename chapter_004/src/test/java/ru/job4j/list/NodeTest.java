package ru.job4j.list;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The Tests
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class NodeTest {
    private Node<Integer> first = new Node(1, null);
    private Node<Integer> second = new Node<>(2, null);
    private Node<Integer> third = new Node<>(3, null);
    private Node<Integer> four = new Node<>(4, null);

    @Test
    public void checkCycleOfContainer() {
        first.setNext(second);
        second.setNext(third);
        third.setNext(four);
        four.setNext(first);
        assertTrue(first.hasCycle(first));
    }

    @Test
    public void checkCycleOfContainerTwo() {
        first.setNext(second);
        second.setNext(third);
        third.setNext(first);
        four.setNext(null);
        assertTrue(first.hasCycle(first));
    }

    @Test
    public void checkNotCycleOfContainer() {
        first.setNext(second);
        second.setNext(third);
        third.setNext(four);
        four.setNext(null);
        assertFalse(first.hasCycle(first));
    }

    @Test
    public void checkNotCycleOfContainerTwo() {
        first.setNext(second);
        second.setNext(third);
        third.setNext(null);
        four.setNext(first);
        assertFalse(first.hasCycle(first));
    }
}
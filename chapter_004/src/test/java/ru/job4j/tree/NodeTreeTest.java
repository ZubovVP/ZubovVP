package ru.job4j.tree;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests
 *
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class NodeTreeTest {

    @Test
    public void when6ElFindLastThen6() {
        Node<Integer> tree = new Node<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Node<Integer> tree = new Node<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAdd7ElementsAndTestIterator() {
        Node<Integer> tree = new Node<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        tree.add(1, 7);

        Iterator<Integer> itr = tree.iterator();
        assertTrue(itr.hasNext());
        assertThat(itr.next(), is(1));
        assertTrue(itr.hasNext());
        assertThat(itr.next(), is(2));
        assertTrue(itr.hasNext());
        assertThat(itr.next(), is(3));
        assertTrue(itr.hasNext());
        assertThat(itr.next(), is(4));
        assertTrue(itr.hasNext());
        assertThat(itr.next(), is(7));
        assertTrue(itr.hasNext());
        assertThat(itr.next(), is(5));
        assertTrue(itr.hasNext());
        assertThat(itr.next(), is(6));
        assertFalse(itr.hasNext());
        itr.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddDuplecateElementAndCheckConcurrentModificationException() {
        Node<Integer> tree = new Node<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 3);
        tree.add(1, 3);

        Iterator<Integer> itr = tree.iterator();
        assertTrue(itr.hasNext());
        assertThat(itr.next(), is(1));
        assertTrue(itr.hasNext());
        assertThat(itr.next(), is(2));
        assertTrue(itr.hasNext());
        assertThat(itr.next(), is(3));
        assertFalse(itr.hasNext());
        tree.add(1, 4);
        itr.next();
    }

    @Test
    public void whenAdd6ElementIsBinaryTree() {
        Node<Integer> tree = new Node<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.isBinary(), is(true));
    }

    @Test
    public void whenAdd6ElementIsNotBinaryTree() {
        Node<Integer> tree = new Node<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(4, 5);
        tree.add(1, 6);
        assertThat(tree.isBinary(), is(false));
    }
}
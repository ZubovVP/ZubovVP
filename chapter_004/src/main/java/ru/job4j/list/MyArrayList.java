package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class MyArrayList<E> implements Iterable<E>, List<E>, Cloneable {

    private static final int DEFAULT_CAPACITY = 10;

    @Override
    public MyArrayList clone() throws CloneNotSupportedException {
        return (MyArrayList) super.clone();
    }

    @GuardedBy("this")
    private E[] container;
    private int size = 0;
    private int modCount = 0;

    public MyArrayList() {
        this.container = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        this.container = (E[]) new Object[initialCapacity];
    }

    /**
     * This method get size of MyArrayList.
     *
     * @return size of MyArrayList.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * This method add element in the MyArrayList.
     *
     * @param e - element.
     * @return - result action.
     */
    @Override
    public synchronized boolean add(E e) {
        ensureCapacity(size + 1);
        this.container[size++] = e;
        modCount++;
        return true;
    }

    /**
     * This method expand the MyArrayList.
     *
     * @param count - size of MyArrayList.
     */
    private void ensureCapacity(int count) {
        if (count > container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    /**
     * This method return element on index.
     *
     * @param index - index.
     * @return - element.
     */
    @Override
    public E get(int index) {
        return this.container[index];
    }

    /**
     * This method check element in container.
     *
     * @param e - element.
     * @return - result.
     */
    public boolean contains(E e) {
        boolean result = false;
        for (E element : container) {
            if (element == e) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Iterator.
     *
     * @return - Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        int expectedModCount = this.modCount;

        return new Iterator<E>() {
            private int step = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return step < size;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                } else if (size <= step) {
                    throw new NoSuchElementException();
                }
                return container[step++];
            }
        };
    }
}

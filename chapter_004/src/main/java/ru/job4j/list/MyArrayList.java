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
public class MyArrayList<E> implements Iterable<E>, Cloneable {
    @GuardedBy("this")
    private E[] container;
    private int size = 0;
    private int modCount = 0;
    private static final int DEFAULT_CAPACITY = 10;

    @Override
    public MyArrayList clone() throws CloneNotSupportedException {
        return (MyArrayList) super.clone();
    }

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
    public int size() {
        return this.size;
    }

    /**
     * This method add element in the MyArrayList.
     *
     * @param e - element.
     * @return - result action.
     */
    public synchronized boolean add(E e) {
        ensureCapacity(size + 1);
        this.container[size++] = e;
        this.modCount++;
        return true;
    }

    /**
     * This method add element in the MyArrayList.
     *
     * @param e - element.
     * @return - result action.
     */
    public synchronized boolean add(int index, E e) {
        Objects.checkIndex(index, this.size - 1);
        ensureCapacity(this.size + 1);
        System.arraycopy(this.container, index, this.container, index + 1, ++this.size - index);
        this.container[index] = e;
        this.modCount++;
        return true;
    }

    /**
     * This method expand the MyArrayList.
     *
     * @param count - size of MyArrayList.
     */
    private void ensureCapacity(int count) {
        if (count > this.container.length) {
            this.container = Arrays.copyOf(container, container.length * 2);
        }
    }

    /**
     * This method return element on index.
     *
     * @param index - index.
     * @return - element.
     */
    public E get(int index) {
        Objects.checkIndex(index, this.size);
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
            if (element.equals(e)) {
                result = true;
                break;
            }
        }
        return result;
    }


    /**
     * Replace element.
     *
     * @param index   - index.
     * @param element - element.
     * @return - element.
     */
    public E set(int index, E element) {
        Objects.checkIndex(index, this.size);
        this.container[index] = element;
        return element;
    }

    /**
     * Delete element from list.
     *
     * @param index - index.
     * @return - removed of element.
     */
    public E remove(int index) {
        Objects.checkIndex(index, this.size);
        E removed = this.container[index];
        System.arraycopy(this.container, index + 1, this.container, index, --this.size - index);
        this.container[size] = null;
        this.modCount++;
        return removed;
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

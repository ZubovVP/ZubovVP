package ru.job4j.set;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class MyHashSet<E> implements Iterable<E> {
    private E[] table;
    private int count = 0;

    public MyHashSet(int initialCapacity) {
        this.table = (E[]) new Object[initialCapacity];
    }

    public MyHashSet() {
        this.table = (E[]) new Object[16];
    }

    /**
     *  Check element in MyHashSet.
     *
     * @param e - element.
     * @return - result.
     */
    public boolean contains(E e) {
        return this.table[calculatePosition(e, this.table.length)] != null;
    }

    /**
     * Add element in MyHashSet.
     *
     * @param e - element.
     * @return - result.
     */
    public boolean add(E e) {
        checkcapacity();
        boolean result = false;
        if (!contains(e)) {
            this.table[calculatePosition(e, this.table.length)] = e;
            result = true;
            count++;
        }
        return result;
    }

    /**
     * Remove element from MyHashSet.
     *
     * @param e - element.
     * @return - result.
     */
    public boolean remove(E e) {
        boolean result = false;
        if (contains(e)) {
            this.table[calculatePosition(e, this.table.length)] = null;
            result = true;
            count--;
        }
        return result;
    }

    /**
     * Check capacity of MyHashSet.
     */
    private void checkcapacity() {
        if ((double) count / this.table.length >= 0.75) {
            E[] newTable = (E[]) new Object[this.table.length * 2];
            for (E count : this.table) {
                newTable[calculatePosition(count, newTable.length)] = count;
            }
            this.table = newTable;
        }
    }

    /**
     * Calculate number position for element.
     *
     * @param e - element.
     * @return - number position.
     */
    private int calculatePosition(E e, int size) {
        return Math.abs(e.hashCode() % this.table.length);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int step = 0;
            int returnElement = 0;
            int expectedModCount = count;

            /**
             * check correct of the table.
             *
             * @param expectedModCount - count.
             */
            private void checkConcurrentModificationException(int expectedModCount) {
                if (expectedModCount != count) {
                    throw new ConcurrentModificationException();
                }
            }

            /**
             * Check next element.
             *
             * @return - result.
             */
            @Override
            public boolean hasNext() {
                checkConcurrentModificationException(expectedModCount);
                return count > returnElement;
            }

            /**
             * Return next count.
             *
             * @return - count.
             */
            @Override
            public E next() {
                E result = null;
                checkConcurrentModificationException(expectedModCount);
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                for (int x = step; x < table.length; x++) {
                    if (table[x] != null) {
                        result = table[x];
                        step++;
                        break;
                    }
                    step++;
                }
                returnElement++;
                return result;
            }
        };
    }
}

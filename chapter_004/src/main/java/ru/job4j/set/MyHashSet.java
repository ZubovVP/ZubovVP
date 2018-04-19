package ru.job4j.set;

import java.util.Arrays;
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
        boolean result = false;
        for (E element : this.table) {
            if (element != null && element.hashCode() == e.hashCode() && element.equals(e)) {
                result = true;
                break;
            }
        }
        return result;
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
            this.table[calculatePosition(e)] = e;
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
            this.table[calculatePosition(e)] = null;
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
            this.table = Arrays.copyOf(this.table, this.table.length * 2);
        }
    }

    /**
     * Calculate number position for element.
     *
     * @param e - element.
     * @return - number position.
     */
    private int calculatePosition(E e) {
        return Math.abs(e.hashCode() % this.table.length - 1);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int step = 0;
            int expectedModCount = count;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (expectedModCount != count) {
                    throw new ConcurrentModificationException();
                }
                for (int x = step; x < table.length; x++) {
                    if (table[x] != null) {
                        result = true;
                    }
                }
                return result;
            }

            @Override
            public E next() {
                E result = null;
                if (expectedModCount != count) {
                    throw new ConcurrentModificationException();
                }
                for (int x = step; x < table.length; x++) {
                    if (table[x] != null) {
                        result = table[x];
                        step++;
                        break;
                    }
                    step++;
                }
                    if (result == null) {
                        throw new NoSuchElementException();
                    }
                return result;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MyHashSet<?> myHashSet = (MyHashSet<?>) o;

        if (count != myHashSet.count) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(table, myHashSet.table);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(table);
        result = 31 * result + count;
        return result;
    }
}

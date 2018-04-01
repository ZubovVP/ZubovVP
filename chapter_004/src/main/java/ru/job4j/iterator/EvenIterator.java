package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class EvenIterator implements Iterator {
    private final int[] values;
    private int position = 0;

    public EvenIterator(int[] values) {
        this.values = values;
    }

    /**
     *  Method hasNext.
     *
     * @return - result condition.
     */
    @Override
    public boolean hasNext() {
        int test = position;
        boolean result = false;
        while (test < values.length) {
            if (values[test] % 2 == 0) {
                result = true;
                break;
            } else {
                test++;
            }
        }
        if (position == values.length && !result) {
            throw new NoSuchElementException();
        }
        return result;
    }

    /**
     *  Method next.
     *
     * @return - Object.
     */
    @Override
    public Object next() {
        while (position < values.length) {
            if (values[position] % 2 == 0) {
                break;
            } else {
                position++;
            }
        }
        if (position == values.length) {
            throw new NoSuchElementException();
        }
        return values[position++];
    }
}

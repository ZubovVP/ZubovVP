package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *@author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class PrimeIterator implements Iterator {
    private final int[] values;
    private int index = 0;

    public PrimeIterator(int[] values) {
        this.values = values;
    }

    /**
     * Method hasNext.
     *
     * @return - result condition.
     */
    @Override
    public boolean hasNext() {
        int step = index;
        boolean result = false;
        while (values.length > step) {
            if (values[index] > 1 && (values[step] == 3 || values[step] == 2 || values[step] == 5 || values[step] == 7)) {
                result = true;
                break;
            } else if (values[step] % 2 != 0 && values[step] % 3 != 0  && values[step] % 5 != 0 &&  values[step] % 7 != 0) {
                result = true;
                break;
            } else {
                step++;
            }
        }
        return result;
    }

    /**
     * Method next.
     *
     * @return - Object
     */
    @Override
    public Object next() {
        while (values.length > index) {
            if (values[index] > 1 && (values[index] == 3 || values[index] == 2 || values[index] == 5 || values[index] == 7)) {
                break;
            } else if (values[index] > 1 && values[index] % 2 != 0 && values[index] % 3 != 0  && values[index] % 5 != 0 &&  values[index] % 7 != 0) {
                break;
            } else {
                index++;
            }
        }
       if (index >= values.length) {
            throw new NoSuchElementException();
        }
        return values[index++];
    }
}

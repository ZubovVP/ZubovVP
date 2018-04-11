package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *@author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class IteratorArray implements Iterator<Integer> {
    private final int[][] values;
    private int indexInside = 0;
    private int indexOutside = 0;

    public IteratorArray(int[][] values) {
        this.values = values;
    }

    /**
     * Method hasNext.
     *
     * @return - result condition.
     */
    @Override
    public boolean hasNext() {
        boolean result = true;
        if ((values.length - 1) <= indexOutside && indexInside > (values[indexOutside].length) - 1) {
            result = false;
        }
        return result;
    }

    /**
     * Method next.
     *
     * @return - count.
     */
    @Override
    public Integer next() {
         int result = 0;
            try {
                if (indexInside < values[indexOutside].length && indexOutside < values.length) {
                    result = values[indexOutside][indexInside++];
                } else if (indexOutside < values.length && values[indexOutside].length == indexInside) {
                    indexInside = 0;
                    result = values[++indexOutside][indexInside++];
                }
                } catch (ArrayIndexOutOfBoundsException exception) {
                    throw  new NoSuchElementException();
                }
        return result;
    }
}

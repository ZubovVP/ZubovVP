package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *@author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class PrimeIterator implements Iterator<Integer> {
    private final int[] values;
    private int index = 0;
    private boolean[] patern = getSieve();

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
           if (patern[values[step]]) {
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
     * @return - count.
     */
    @Override
    public Integer next() {
        while (hasNext()) {
           if (patern[values[index]]) {
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
    private static boolean[] getSieve() {
        boolean[] primes = new boolean[100000];
        for (int x = 0; x < primes.length; x++) {
            if (x > 1 && x == 3 || x == 2 || x == 5 || x == 7 || x == 11) {
                primes[x] = true;
            } else if (x > 1 && x  % 2 != 0 && x % 2 != 0 && x % 5 != 0 && x % 7 != 0 && x % 11 != 0) {
                primes[x] = true;
            }
        }
        return primes;
    }
}



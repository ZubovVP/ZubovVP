package ru.job4j.iterator;

import java.util.Iterator;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Converter  {

    /**
     * Method can to get iterator of iterators and  take iterator.
     *
     * @param it - iterator of iterators.
     * @return - iterator.
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Iterator<Integer>> iteratorIterator = it;
            Iterator<Integer> inside = iteratorIterator.next();

            private void check() {
                if (!this.inside.hasNext() && this.iteratorIterator.hasNext()) {
                    this.inside = this.iteratorIterator.next();
                }
            }

            @Override
            public boolean hasNext() {
                check();
                return inside.hasNext();
            }

            @Override
            public Integer next() {
                check();
                return this.inside.next();
            }
        };
    }
}


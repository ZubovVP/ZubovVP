package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Converter  {
    private Iterator<Iterator<Integer>> it = null;
    private List<Integer> list = new ArrayList<>();

    /**
     * Method can to get iterator of iterators and  take iterator.
     *
     * @param it - iterator of iterators.
     * @return - iterator.
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        this.it = it;
        while (this.it.hasNext()) {
            Iterator<Integer> inside = this.it.next();
            while (inside.hasNext()) {
                list.add(inside.next());
            }
        }
        return list.iterator();
    }
}


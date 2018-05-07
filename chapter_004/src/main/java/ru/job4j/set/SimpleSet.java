package ru.job4j.set;
import ru.job4j.list.MyArrayList;

import java.util.Iterator;


/**
 * SimpleSet.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class SimpleSet<E> implements Iterable<E> {
private MyArrayList<E> list;
private int modCount = 0;

    public SimpleSet() {
        this.list = new MyArrayList<>(1000);
    }

    /**
     * Add element in SimpleSet.
     *
     * @param e - element.
     */
    public void add(E e) {
        if (!list.contains(e)) {
            list.add(e);
            modCount++;
        }
    }

    /**
     * Iterator.
     *
     * @return - iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Iterator<E> itr = list.iterator();
            @Override
            public boolean hasNext() {
                return itr.hasNext();
            }

            @Override
            public E next() {
                return itr.next();
            }
        };
    }
}

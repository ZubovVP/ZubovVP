package ru.job4j.set;

import ru.job4j.list.MyLinkedList;

import java.util.Iterator;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class SimpleLinkedSet<E> implements Iterable<E> {
    private MyLinkedList<E> list;

    public SimpleLinkedSet() {
        this.list = new MyLinkedList<>();
    }

    /**
     * Add element in SimpleLinkedSet.
     *
     * @param e - element.
     */
    public void add(E e) {
        boolean result = false;
        for (E element : list) {
            if (element == e) {
                result = true;
                break;
            }
        }
        if (!result) {
            list.add(e);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Iterator<E> itr = list.iterator();
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

package ru.job4j.list;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public interface List<E> {
    int size();
    boolean add(E e);
    E get(int index) throws NoSuchFieldException;
}

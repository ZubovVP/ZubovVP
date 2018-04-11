package ru.job4j.list;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public interface StackAndQueue<E> {
     E poll() throws NoSuchFieldException;
     void push(E value);
}

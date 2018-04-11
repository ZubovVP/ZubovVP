package ru.job4j.list;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class MyQueue<E> extends MyLinkedList<E> implements StackAndQueue<E> {

    /**
     * This method return first element from MyQueue and remove it from MyQueue.
     * @return - first element.
     */
    public E poll() {
        E e = get(0);
        remove(0);
        return e;
    }

    /**
     * Return first element in the MyQueue.
     * @return - first element in the MyQueue.
     */
    public E peek() {
        return this.get(0);
    }

    /**
     * This method add element in the MyQueue.
     * @param value - element.
     */
     public void push(E value) {
         add(value);
     }
}

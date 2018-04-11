package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class MyLinkedList<E> implements List<E>, Iterable<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    /**
     * This method get size of MyLinkedList.
     * @return size of MyLinkedList.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * This method add object in MyLinkedList.
     * @param e - element.
     * @return - return result action.
     */
    @Override
    public boolean add(E e) {
        if (this.head == null) {
            Node<E> newNode = new Node<E>(null, e, null);
            this.head = newNode;
            this.tail = newNode;
        } else {
            Node temp = this.tail;
            temp.setNext(new Node<E>(temp, e, null));
            this.tail = temp.getNext();
        }
        this.size++;
        return true;
    }

    /**
     * This method return element on the index in the MyLinkedList.
     * @param index - index.
     * @return - element from MyLinkedList.
     */
    @Override
    public E get(int index) {
        return findNode(index).getItem();
    }

    /**
     * This method find node in the MyLinkedList.
     * @param index - index.
     * @return - Node.
     */
    public Node<E> findNode(int index) {
        int count;
        Node result;

        if (size / 2 >= index) {
            result = head;
            count = 0;
            while (index > count++) {
                result = result.getNext();
            }
        } else {
            result = tail;
            count = size - 1;
            while (index != count--) {
                result = result.getPerv();
            }
        }
        return result;
    }

    /**
     * This method remove object from MyLinkedList.
     * @param index - index.
     */
    public void remove(int index)  {
        Node<E> result = findNode(index);
        if (result.getNext() == null && result.getPerv() != null) {
            result.getPerv().setNext(null);
            this.tail = result.getPerv();
        } else if (result.getPerv() == null && result.getNext() != null) {
            this.head = result.getNext();
            result.getNext().setPerv(null);
        } else if (result.getPerv() == null && result.getNext() == null) {
            this.head = null;
            this.tail = null;
        } else {
            result.getPerv().setNext(result.getNext());
        }
        size--;
    }

    /**
     * Iterator
     * @return - Iterator<E>
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
           private final int expectedModCount = size;
            Node<E> result = head;

            @Override
            public boolean hasNext() {
                    if (expectedModCount != size) {
                        throw new ConcurrentModificationException();
                    }
               try {
                   return result.getItem() != null;
               } catch (NullPointerException npe) {
                   return false;
               }
            }

            @Override
            public E next() {
                if (expectedModCount != size) {
                    throw new ConcurrentModificationException();
                }
                E e = result.getItem();
                result = result.getNext();
                return e;
            }
        };
    }

    /**
     *  Node class.
     * @param <E> - Node
     */
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> perv;

        public Node(Node<E> perv, E item, Node<E> next) {
            this.item = item;
            this.next = next;
            this.perv = perv;
        }

        public E getItem() {
            return item;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public Node<E> getPerv() {
            return perv;
        }

        public void setPerv(Node<E> perv) {
            this.perv = perv;
        }
    }
}

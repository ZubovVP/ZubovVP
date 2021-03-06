package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class MyLinkedList<E> implements Iterable<E> {
    @GuardedBy("this")
    private Node<E> head;
    @GuardedBy("this")
    private Node<E> tail;
    private int size = 0;

    /**
     * This method get size of MyLinkedList.
     *
     * @return size of MyLinkedList.
     */
    public int size() {
        return this.size;
    }

    /**
     * This method add object in MyLinkedList.
     *
     * @param e - element.
     * @return - return result action.
     */
    public synchronized boolean add(E e) {
        if (this.head == null) {
            Node<E> newNode = new Node<>(null, e, null);
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
     * This method add object in MyLinkedList.
     *
     * @param e - element.
     * @return - return result action.
     */
    public synchronized boolean add(int index, E e) {
        Node<E> newElement;
        Objects.checkIndex(index, this.size);
        if (this.head == null) {
            Node<E> newNode = new Node<>(null, e, null);
            this.head = newNode;
            this.tail = newNode;
        } else {
            Node<E> element = findNode(index);
            if (element.equals(this.head)) {
                newElement = new Node<>(null, e, element);
                this.head = newElement;
            } else {
                newElement = new Node<>(element.getPerv(), e, element);
                element.getPerv().setNext(newElement);
            }
            element.setPerv(newElement);
        }
        this.size++;
        return true;
    }

    /**
     * This method return element on the index in the MyLinkedList.
     *
     * @param index - index.
     * @return - element from MyLinkedList.
     */
    public E get(int index) {
        Objects.checkIndex(index, this.size);
        return findNode(index).getItem();
    }

    /**
     * This method find node in the MyLinkedList.
     *
     * @param index - index.
     * @return - Node.
     */
    private Node<E> findNode(int index) {
        Objects.checkIndex(index, this.size);
        int count;
        Node result;

        if (size / 2 >= index - 1) {
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
     *
     * @param index - index.
     */
    public synchronized void remove(int index) {
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
     * Set element from list
     *
     * @param index   - index.
     * @param element - element.
     * @return - element.
     */
    public E set(int index, E element) {
        Node<E> replaceElement = findNode(index);
        Node<E> result = new Node<>(replaceElement.getPerv(), element, replaceElement.getNext());
        replaceElement.getPerv().setNext(result);
        replaceElement.getNext().setPerv(result);
        return element;
    }

    /**
     * Iterator
     *
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
     * Node class.
     *
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

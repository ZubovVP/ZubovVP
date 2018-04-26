package ru.job4j.tree;

import java.util.*;

/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Node<E extends Comparable<E>> implements SimpleTree<E> {
    private final List<Node<E>> children = new ArrayList<>();
    private final E value;
    private int modCount;

    /**
     * Constructor
     *
     * @param value - value of element.
     */
    public Node(final E value) {
        this.value = value;
    }

    /**
     * Add element in the Tree.
     *
     * @param parent parent.
     * @param child  child.
     * @return - result.
     */
    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> parentNode = findBy(parent);
        boolean result = false;
        if (parentNode.isPresent() && !findBy(child).isPresent()) {
            parentNode.get().children.add(new Node<>(child));
            result = true;
            modCount++;
        }
        return result;
    }


    /**
     * Return list of children.
     *
     * @return - List.
     */
    public List<Node<E>> leaves() {
        return this.children;
    }

    /**
     * Find Node for help value.
     *
     * @param value - value.
     * @return - Node.
     */
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Check is binary this tree.
     *
     * @return - result.
     */
    @Override
    public boolean isBinary() {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this);
        boolean result = true;
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.children.size() > 2) {
                result = false;
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }
    /**
     * Compare element with this value.
     *
     * @param that - element.
     * @return - result.
     */
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }


    /**
     * Iterator.
     *
     * @return - iterator.
     */
    @Override
    public Iterator<E> iterator() {
        int expectCount = modCount;
        return new Iterator<E>() {
            int count = 0;
            List<E> list = checkList();
            private List<E>  checkList() {
                Queue<Node<E>> data = new LinkedList<>();
                List<E> list = new ArrayList<>();
                data.offer(Node.this);
                while (!data.isEmpty()) {
                    Node<E> el = data.poll();
                    list.add(el.value);
                    for (Node<E> child : el.leaves()) {
                        data.offer(child);
                    }
                }
                return list;
            }

            @Override
            public boolean hasNext() {
                if (expectCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < list.size();
            }

            @Override
            public E next() {
                if (expectCount != modCount) {
                    throw new ConcurrentModificationException();
                } else if (count == list.size()) {
                   throw new NoSuchElementException();
                }
                return list.get(count++);
            }
        };
    }
}

package ru.job4j.tree;

import java.util.*;

/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 *
 * MyTree class.
 * @param <E> - typ of the element.
 */
public class MyTree<E extends Comparable<E>> {
    private Node<E> root;
    private int count;

    /**
     * Constructor.
     *
     * @param value - element.
     */
    public MyTree(E value) {
        this.root = new Node<>(value);
    }

    /**
     * Add element in the MyTree.
     *
     * @param e - element.
     * @return - result.
     */
    public E add(E e) {
        Node<E> current = this.root;
        while (!current.value.equals(e)) {
            current = checkTree(current, e);
        }
        return current.value;
    }

    /**
     * Check node with value if value of the node doesn't equals value return child of node.
     * If value of node equals value, create new node and return it.
     *
     * @param current - Node.
     * @param value - value.
     * @return - node.
     */
    private Node<E> checkTree(Node<E> current, E value) {
        Node<E> result = current;
        if (result.getValue().compareTo(value) >= 0) {
            if (result.getLeftChild() == null) {
                result.setLeftChild(new Node<>(value));
                count++;
            } else {
                result = result.getLeftChild();
            }
        } else if (result.getValue().compareTo(value) < 0) {
            if (result.getRightChild() == null) {
                result.setRightChild(new Node<>(value));
                count++;
            } else {
                result = result.getRightChild();
            }
        }
        return result;
    }


    /**
     * Iterator of the MyTree.
     *
     * @return - iterator.
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectCount = count;
            private int step;
            private List<E> list = checkList();

            private List<E> checkList() {
                Queue<Node<E>> data = new LinkedList<>();
                List<E> list = new ArrayList<>();
                data.offer(root);
                while (!data.isEmpty()) {
                    Node<E> el = data.poll();
                    list.add(el.value);
                    if (el.rightChild != null) {
                        data.offer(el.rightChild);
                    } else if (el.leftChild != null) {
                        data.offer(el.leftChild);
                    }
                }
                return list;
            }

            @Override
            public boolean hasNext() {
                if (expectCount != count) {
                    throw new ConcurrentModificationException();
                }

                return step < list.size();
            }

            @Override
            public E next() {
                if (expectCount != count) {
                    throw new ConcurrentModificationException();
                } else if (step == list.size()) {
                    throw new NoSuchElementException();
                }
                return list.get(step++);
            }
        };
    }

    /**
     * @author Vitaly Zubov (zubovvp@yandex.ru)
     * @version $Id$
     * @since 0.1
     *
     * Node class.
     * Inside element of MyTree.
     *
     * @param <E> - typ of the element.
     */
    private class Node<E> {
        private E value;
        private Node<E> leftChild;
        private Node<E> rightChild;

        /**
         * Constructor.
         *
         * @param value - value.
         */
        public Node(E value) {
            this.value = value;
        }

        /**
         * Return value of the node.
         *
         * @return - value.
         */
        public E getValue() {
            return value;
        }

        /**
         * Return leftChild of the node.
         *
         * @return - node.
         */
        public Node<E> getLeftChild() {
            return leftChild;
        }

        /**
         * Set leftChild
         *
         * @param leftChild - node.
         */
        public void setLeftChild(Node<E> leftChild) {
            this.leftChild = leftChild;
        }

        /**
         * Return rightChild of the node.
         *
         * @return - node.
         */
        public Node<E> getRightChild() {
            return rightChild;
        }

        /**
         * Set rightChild.
         *
         * @param rightChild - node.
         */
        public void setRightChild(Node<E> rightChild) {
            this.rightChild = rightChild;
        }
    }
}

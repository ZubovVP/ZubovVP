package ru.job4j.list;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Node<T> {
    private T value;
    private Node<T> next;

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    /**
     * This method check cycle of container.
     * @param first - first element in the container.
     * @return - result.
     */
    boolean hasCycle(Node<T> first) {
        boolean result = false;
        Node<T> fast = first;
        Node<T> slow = first;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public static void main(String[] args) {
        Node<Integer> test1 = new Node(1, null);
        Node<Integer> test2 = new Node<>(2, null);
        Node<Integer> test3 = new Node<>(3, null);
        Node<Integer> test4 = new Node<>(4, null);
        test1.next = test2;
        test2.next = test3;
        test3.next = null;
        test4.next = test1;
        System.out.println(test1.hasCycle(test1));


    }
}

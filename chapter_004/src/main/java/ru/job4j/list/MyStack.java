package ru.job4j.list;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class MyStack<E> extends MyLinkedList<E> implements StackAndQueue<E>  {

    /**
     * This method return last element from MyStack and remove this element from MyStack.
     * @return - element.
     */
    @Override
    public E poll()  {
        E e = this.get(size() - 1);
        remove(size() - 1);
        return e;
    }
    /**
     * Return last element in the MyStack.
     * @return - last element in the MyStack.
     */
    public E peek() {
        return this.get(size() - 1);
    }


    /**
     * Add element in the MyStack.
     * @param value - element.
     */
    @Override
    public void push(E value) {
        add(value);
    }

    public static void main(String[] args) {
        MyStack<Integer> test = new MyStack<>();
        test.push(1);
        test.push(2);
        test.push(3);
        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.poll());
    }
}

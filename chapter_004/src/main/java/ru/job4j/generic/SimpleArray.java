package ru.job4j.generic;

import java.util.Iterator;
import java.util.Objects;

/**
 * @param <T> - SimpleArray - list.
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class SimpleArray<T> implements Iterator<T> {

    private Object[] list;
    private int step = 0;
    private int index = 0;

    public SimpleArray(int size) {
        this.list = new Object[size];
    }

    /**
     * Add model in list.
     *
     * @param model - model.
     */
    public void add(T model) {
        this.list[this.index++] = model;
    }

    /**
     * Method can to replace model in list.
     *
     * @param index - index of model.
     * @param model - model.
     */
    public void set(int index, T model) {
        Objects.checkIndex(index, this.index);
        this.list[index] = model;
    }

    /**
     * Method can to remove in list.
     *
     * @param index - index in the list.
     */
    public void delete(int index) {
        Objects.checkIndex(index, this.index);
        System.arraycopy(this.list, index + 1, this.list, index, this.list.length - this.index--);
    }

    /**
     * Method can give model.
     *
     * @param index - index in the list.
     * @return - model.
     */
    public T get(int index) {
        Objects.checkIndex(index, this.index);
        return (T) this.list[index];
    }


    /**
     * Method hasNext.
     *
     * @return - result condition.
     */
    @Override
    public boolean hasNext() {
        return step < this.index;
    }

    /**
     * Method next.
     *
     * @return - model.
     */
    @Override
    public T next() {
        return (T) list[step++];
    }
}

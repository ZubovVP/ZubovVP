package ru.job4j.generic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 * @param <T> - SimpleArray - list.
 */
public class SimpleArray<T> implements Iterator<T> {

    private List<T> list = new ArrayList<T>();
    private int position = 0;
    private int step = 0;


    /**
     * Add model in list.
     *
     * @param model - model.
     */
    public void add(T model) {
        list.add(model);
        position++;
    }

    /**
     * Method can to replace model in list.
     *
     * @param index - index of model.
     * @param model - model.
     */
    public void set(int index, T model) {
        list.set(index, model);
    }

    /**
     * Method can to remove in list.
     *
     * @param index - index in the list.
     */
    public void delete(int index) {
        list.remove(index);
        position--;
    }

    /**
     * Method can give model.
     *
     * @param index - index in the list.
     * @return - model.
     */
    public T get(int index) {
        return list.get(index);
    }


    /**
     * Method hasNext.
     *
     * @return - result condition.
     */
    @Override
    public boolean hasNext() {
        return step < position;
    }

    /**
     * Method next.
     *
     * @return - model.
     */
    @Override
    public T next() {
        return list.get(step++);
    }
}

package ru.job4j.start;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 06.09.2020.
 */
public interface Store<E> {
    E add(E item);

    boolean replace(E item);

    boolean delete(int id);

    List<E> findAll();

    List<E> findByName(String key);

    E findById(int id);
}

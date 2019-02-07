package ru.job4j.storage;

import ru.job4j.models.User;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov
 * Email: Zubov.VP@yandex.ru
 * Version: $Id$
 * Date: 04.12.2018
 */
public interface Store<T> {
    boolean add(T element);

    boolean update(T element);

    boolean delete(int id);

    List<T> findAll();

    User findById(int id);

    void close();
}

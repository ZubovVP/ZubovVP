package ru.job4j.start;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 01.03.2021.
 */
public interface Observe<T> {
    void receive(T model);
}


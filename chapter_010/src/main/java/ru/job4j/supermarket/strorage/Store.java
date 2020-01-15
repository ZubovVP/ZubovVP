package ru.job4j.supermarket.strorage;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 26.11.2019.
 */
public interface Store<E> {
    boolean accept(E food);
}

package ru.job4j.supermarket;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 15.12.2019.
 */
public interface Actions<E> {
    boolean accept(E food);

    void resort();

}

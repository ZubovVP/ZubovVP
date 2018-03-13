package ru.job4j.start;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public interface UserAction {
    int key();

    void execute(Input input, Tracker tracker);

    String info();
}

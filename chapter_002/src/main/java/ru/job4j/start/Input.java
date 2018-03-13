package ru.job4j.start;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Input {
    String ask(String question);

    int ask(String question, int[] range) throws MenuOutException;
}

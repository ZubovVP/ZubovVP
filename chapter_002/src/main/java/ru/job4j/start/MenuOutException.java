package ru.job4j.start;

/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class MenuOutException extends RuntimeException {
    public MenuOutException(String msg) {
        super((msg));
    }
}

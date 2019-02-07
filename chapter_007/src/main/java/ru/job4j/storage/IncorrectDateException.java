package ru.job4j.storage;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov
 * Email: Zubov.VP@yandex.ru
 * Version: $Id$
 * Date: 06.12.2018
 */
public class IncorrectDateException extends RuntimeException {
    /**
     * Constructor.
     *
     * @param description - description.
     */
    public IncorrectDateException(String description) {
        super(description);
    }

    /**
     * Constructor.
     */
    public IncorrectDateException() {
        super();
    }
}

package ru.job4j.servlets;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov
 * Email: Zubov.VP@yandex.ru
 * Version: $Id$
 * Date: 06.12.2018
 */
public class IncorrectDateException extends Throwable {
    /**
     * Constructor.
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

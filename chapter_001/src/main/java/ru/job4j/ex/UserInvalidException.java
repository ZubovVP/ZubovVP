package ru.job4j.ex;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 20.12.2020.
 */
public class UserInvalidException extends UserNotFoundException {

    public UserInvalidException(String message) {
        super(message);
    }
}

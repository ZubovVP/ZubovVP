package ru.job4j.blocking;

/**
 * Created by ZubovVP on 02.07.2018
 * zubovvp@yadndex.ru
 */
public class OptinisticException extends RuntimeException {
    public OptinisticException() {
    }

    public OptinisticException(String message) {
        super(message);
    }

    public OptinisticException(String message, Throwable cause) {
        super(message, cause);
    }

    public OptinisticException(Throwable cause) {
        super(cause);
    }

    public OptinisticException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

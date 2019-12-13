package ru.job4j.calculator.operations;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 10.12.2019.
 */
public abstract class AbstractOperation implements Action {
    protected String name;

    public String getName() {
        return name;
    }
}

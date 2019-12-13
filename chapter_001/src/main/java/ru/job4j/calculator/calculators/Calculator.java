package ru.job4j.calculator.calculators;

import java.util.Set;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 08.12.2019.
 */
public interface Calculator {
    double operation(String op, double operand);

    double operation(String op, double left, double right);

    void fillActions();

    Set<String> getActions();
}
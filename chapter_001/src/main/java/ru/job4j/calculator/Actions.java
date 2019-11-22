package ru.job4j.calculator;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 20.11.2019.
 */
public interface Actions {
    double add(double first, double second);

    double subtract(double first, double second);

    double div(double first, double second);

    double multiple(double first, double second);

    double sin(double amount);

    double sinh(double amount);

    double cos(double amount);

    double cosh(double amount);

    double tg(double amount);

    double arctg(double amount);

    double ctg(double amount);

    double arcctg(double amount);
}

package ru.job4j.calculator;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 16.11.2019.
 */
public class EngCalculator extends Calculator {

    /**
     * Returns the trigonometric sin.
     *
     * @param amount - amount.
     * @return - result.
     */
    @Override
    public double sin(double amount) {
        return Math.sin(amount);
    }

    /**
     * Returns the trigonometric sinh.
     *
     * @param amount - amount.
     * @return - result.
     */
    @Override
    public double sinh(double amount) {
        return Math.sinh(amount);
    }

    /**
     * Returns the trigonometric cos.
     *
     * @param amount - amount.
     * @return - result.
     */
    @Override
    public double cos(double amount) {
        return Math.cos(amount);
    }

    /**
     * Returns the trigonometric cosh.
     *
     * @param amount - amount.
     * @return - result.
     */
    @Override
    public double cosh(double amount) {
        return Math.cosh(amount);
    }

    /**
     * Returns the trigonometric tangent.
     *
     * @param amount - amount.
     * @return - result.
     */
    @Override
    public double tg(double amount) {
        return Math.tan(amount);
    }

    /**
     * Returns the trigonometric arc tangent.
     *
     * @param amount - amount.
     * @return - result.
     */
    @Override
    public double arctg(double amount) {
        return Math.atan(amount);
    }

    /**
     * Returns the trigonometric contingent.
     *
     * @param amount - amount.
     * @return - result.
     */
    @Override
    public double ctg(double amount) {
        return 1 / tg(amount);
    }

    /**
     * Returns the trigonometric arc contingent.
     *
     * @param amount - amount.
     * @return - result.
     */
    @Override
    public double arcctg(double amount) {
        return Math.PI / 2 - arctg(amount);
    }
}

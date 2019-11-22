package ru.job4j.calculator;

/**
 * Calculator.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class Calculator implements Actions {
    /**
     * Программа позволяет производить вычисления на двумя числами.
     *
     * @param first - перое число
     * @param second - второе число
     * @return result - вывод результат вычисления
     */
    private double result;

    /**
     * Метод позволяет производить сложение на двумя числами.
     *
     * @param first  - перое число
     * @param second - второе число
     */
    public double add(double first, double second) {
        this.result = first + second;
        return this.result;
    }

    /**
     * Метод позволяет производить вычитание на двумя числами.
     *
     * @param first  - перое число
     * @param second - второе число
     */
    public double subtract(double first, double second) {
        this.result = first - second;
        return this.result;
    }

    /**
     * Метод позволяет производить деление на двумя числами.
     *
     * @param first  - перое число
     * @param second - второе число
     */
    public double div(double first, double second) {
        this.result = first / second;
        return this.result;
    }

    /**
     * Метод позволяет производить умножение на двумя числами.
     *
     * @param first  - перое число
     * @param second - второе число
     */
    public double multiple(double first, double second) {
        this.result = first * second;
        return this.result;
    }

    /**
     * Returns the trigonometric sin.
     * Doesn't include this class.
     *
     * @param amount - amount.
     * @return - Exception.
     */
    @Override
    public double sin(double amount) {
        error();
        return 0;
    }

    /**
     * Returns the trigonometric sinh.
     * Doesn't include this class.
     *
     * @param amount - amount.
     * @return - Exception.
     */
    @Override
    public double sinh(double amount) {
        error();
        return 0;
    }

    /**
     * Returns the trigonometric cos.
     * Doesn't include this class.
     *
     * @param amount - amount.
     * @return - Exception.
     */
    @Override
    public double cos(double amount) {
        error();
        return 0;
    }

    /**
     * Returns the trigonometric cosh.
     * Doesn't include this class.
     *
     * @param amount - amount.
     * @return - Exception.
     */
    @Override
    public double cosh(double amount) {
        error();
        return 0;
    }

    /**
     * Returns the trigonometric tangent.
     * Doesn't include this class.
     *
     * @param amount - amount.
     * @return - Exception.
     */
    @Override
    public double tg(double amount) {
        error();
        return 0;
    }

    /**
     * Returns the trigonometric arc tangent.
     * Doesn't include this class.
     *
     * @param amount - amount.
     * @return - Exception.
     */
    @Override
    public double arctg(double amount) {
        error();
        return 0;
    }

    /**
     * Returns the trigonometric contingent.
     * Doesn't include this class.
     *
     * @param amount - amount.
     * @return - Exception.
     */
    @Override
    public double ctg(double amount) {
        error();
        return 0;
    }

    /**
     * Returns the trigonometric arc contingent.
     * Doesn't include this class.
     *
     * @param amount - amount.
     * @return - Exception.
     */
    @Override
    public double arcctg(double amount) {
        error();
        return 0;
    }

    /**
     * Returns error.
     *
     * @return - Exception.
     */
    private void error() {
        try {
            throw new Exception("I don't know this operation");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод позволяет выводит результат вычисления.
     *
     * @return result - вывод результата вычисление
     */
    public double getResult() {
        return this.result;
    }
}
package ru.job4j.calculator;

/**
 * Calculator.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class Calculator {
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
     * Метод позволяет выводит результат вычисления.
     *
     * @return result - вывод результата вычисление
     */
    public double getResult() {
        return this.result;
    }
}
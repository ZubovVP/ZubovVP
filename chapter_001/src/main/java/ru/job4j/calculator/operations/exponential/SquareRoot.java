package ru.job4j.calculator.operations.exponential;

import ru.job4j.calculator.operations.AbstractOperation;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 10.12.2019.
 */
public class SquareRoot extends AbstractOperation {
    public SquareRoot() {
        this.name = "sqr";
    }

    @Override
    public double calculate(double[] values) {
        return Math.sqrt(values[0]);
    }
}

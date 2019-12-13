package ru.job4j.calculator.operations.exponential;

import ru.job4j.calculator.operations.AbstractOperation;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 10.12.2019.
 */
public class Power extends AbstractOperation {
    public Power() {
        this.name = "pow";
    }

    @Override
    public double calculate(double[] values) {
        return Math.pow(values[1], values[0]);
    }
}

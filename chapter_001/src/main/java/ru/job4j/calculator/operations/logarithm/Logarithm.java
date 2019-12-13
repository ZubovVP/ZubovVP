package ru.job4j.calculator.operations.logarithm;

import ru.job4j.calculator.operations.AbstractOperation;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 10.12.2019.
 */
public class Logarithm extends AbstractOperation {

    public Logarithm() {
        this.name = "log";
    }

    @Override
    public double calculate(double[] values) {
        return Math.log10(values[0]);
    }
}

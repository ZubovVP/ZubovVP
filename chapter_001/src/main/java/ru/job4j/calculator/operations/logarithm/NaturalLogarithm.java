package ru.job4j.calculator.operations.logarithm;

import ru.job4j.calculator.operations.AbstractOperation;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 10.12.2019.
 */
public class NaturalLogarithm extends AbstractOperation {

    public NaturalLogarithm() {
        this.name = "ln";
    }

    @Override
    public double calculate(double[] values) {
        return Math.log(values[0]);
    }
}

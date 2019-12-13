package ru.job4j.calculator.operations.basic;

import ru.job4j.calculator.operations.AbstractOperation;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 10.12.2019.
 */
public class Devision extends AbstractOperation {
    public Devision() {
        this.name = "/";
    }

    @Override
    public double calculate(double[] values) {
        return values[1] / values[0];
    }
}


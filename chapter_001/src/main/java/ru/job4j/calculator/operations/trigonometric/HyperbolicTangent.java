package ru.job4j.calculator.operations.trigonometric;

import ru.job4j.calculator.operations.AbstractOperation;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 10.12.2019.
 */
public class HyperbolicTangent extends AbstractOperation {

    public HyperbolicTangent() {
        this.name = "tanh";
    }

    @Override
    public double calculate(double[] values) {
        return Math.tanh(values[0]);
    }
}

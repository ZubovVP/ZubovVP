package ru.job4j.calculator.operations.trigonometric;

import ru.job4j.calculator.operations.AbstractOperation;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 10.12.2019.
 */
public class ArcTangent extends AbstractOperation {

    public ArcTangent() {
        this.name = "atan";
    }

    @Override
    public double calculate(double[] values) {
        return Math.atan(values[0]);
    }
}

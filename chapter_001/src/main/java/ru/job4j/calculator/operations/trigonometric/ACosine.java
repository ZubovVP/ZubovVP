package ru.job4j.calculator.operations.trigonometric;

import ru.job4j.calculator.operations.AbstractOperation;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 10.12.2019.
 */
public class ACosine extends AbstractOperation {

    public ACosine() {
        this.name = "acos";
    }

    @Override
    public double calculate(double[] values) {
        return Math.acos(values[0]);
    }
}

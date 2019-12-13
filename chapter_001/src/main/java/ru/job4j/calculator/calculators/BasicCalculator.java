package ru.job4j.calculator.calculators;

import ru.job4j.calculator.operations.AbstractOperation;
import ru.job4j.calculator.operations.basic.Addition;
import ru.job4j.calculator.operations.basic.Devision;
import ru.job4j.calculator.operations.basic.Multiplication;
import ru.job4j.calculator.operations.basic.Substraction;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 08.12.2019.
 */
public class BasicCalculator extends AbstractCalculator {


    @Override
    public void fillActions() {
        AbstractOperation add = new Addition();
        this.actions.put(add.getName(), add);
        AbstractOperation sub = new Substraction();
        this.actions.put(sub.getName(), sub);
        AbstractOperation mult = new Multiplication();
        this.actions.put(mult.getName(), mult);
        AbstractOperation dev = new Devision();
        this.actions.put(dev.getName(), dev);
    }
}

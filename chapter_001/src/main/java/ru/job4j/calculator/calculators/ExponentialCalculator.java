package ru.job4j.calculator.calculators;


import ru.job4j.calculator.operations.AbstractOperation;
import ru.job4j.calculator.operations.exponential.Exponential;
import ru.job4j.calculator.operations.exponential.Power;
import ru.job4j.calculator.operations.exponential.SquareRoot;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 08.12.2019.
 */
public class ExponentialCalculator extends AbstractCalculator {

    @Override
    public void fillActions() {
        AbstractOperation pow = new Power();
        this.actions.put(pow.getName(), pow);
        AbstractOperation sqr = new SquareRoot();
        this.actions.put(sqr.getName(), sqr);
        AbstractOperation exp = new Exponential();
        this.actions.put(exp.getName(), exp);
    }
}

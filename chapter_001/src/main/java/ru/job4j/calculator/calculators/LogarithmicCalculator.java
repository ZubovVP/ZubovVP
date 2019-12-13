package ru.job4j.calculator.calculators;

import ru.job4j.calculator.operations.AbstractOperation;
import ru.job4j.calculator.operations.logarithm.Logarithm;
import ru.job4j.calculator.operations.logarithm.NaturalLogarithm;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 08.12.2019.
 */
public class LogarithmicCalculator extends AbstractCalculator {

    @Override
    public void fillActions() {
        AbstractOperation ln = new NaturalLogarithm();
        this.actions.put(ln.getName(), ln);
        AbstractOperation log = new Logarithm();
        this.actions.put(log.getName(), log);
    }
}

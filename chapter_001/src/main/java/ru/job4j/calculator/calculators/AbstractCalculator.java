package ru.job4j.calculator.calculators;

import ru.job4j.calculator.operations.Action;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 08.12.2019.
 */
public abstract class AbstractCalculator implements Calculator {
    protected Map<String, Action> actions = new HashMap<>();
    private double lastResult;

    /**
     * Calculate with use last result.
     *
     * @param op - action.
     * @param amount - amount
     * @return - result.
     */
    @Override
    public double operation(String op, double amount) {
        lastResult = this.actions.get(op).calculate(new double[]{amount, lastResult});
        return lastResult;
    }


    /**
     * Calculate.
     *
     * @param op - action.
     * @param left - left amount.
     * @param right - right amount.
     * @return - result.
     */
    @Override
    public double operation(String op, double left, double right) {
        lastResult = this.actions.get(op).calculate(new double[]{right, left});
        return lastResult;
    }

    /**
     * Add all actions in a list.
     */
    public abstract void fillActions();

    /**
     * Get actions.
     *
     * @return - list of actions.
     */
    public Set<String> getActions() {
        return this.actions.keySet();
    }

    /**
     * Get last result.
     *
     * @return - last result.
     */
    public double getLastResult() {
        return lastResult;
    }
}

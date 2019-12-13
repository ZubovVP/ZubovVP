package ru.job4j.calculator.calculators;

import java.util.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 09.12.2019.
 */
public class EngCalculator extends AbstractCalculator {
    private final List<AbstractCalculator> simpleCalculator;
    private Map<String, AbstractCalculator> actions;

    /**
     * Constructor.
     *
     * @param simpleCalculators - list with calculators.
     */
    public EngCalculator(List<AbstractCalculator> simpleCalculators) {
        this.simpleCalculator = simpleCalculators;
    }

    /**
     * Fill actions in a list.
     */
    @Override
    public void fillActions() {
        this.actions = new HashMap<>();
        for (AbstractCalculator calculator : this.simpleCalculator) {
            calculator.fillActions();
            for (String action : calculator.getActions()) {
                this.actions.put(action, calculator);
            }
        }
    }

    /**
     * Get actions.
     *
     * @return - list of actions.
     */
    @Override
    public Set<String> getActions() {
        Set<String> actions = new HashSet<>();
        for (AbstractCalculator calculator : this.simpleCalculator) {
            actions.addAll(calculator.getActions());

        }
        return actions;
    }

    /**
     * Calculate with use last result.
     *
     * @param op - action.
     * @param amount - amount
     * @return - result.
     */
    @Override
    public double operation(String op, double amount) {
        return this.actions.get(op).operation(op, amount);
    }

    /**
     * Calculate.
     *
     * @param op - action.
     * @param right - right amount.
     * @param left - left amount.
     * @return - result.
     */
    @Override
    public double operation(String op, double left, double right) {
        return this.actions.get(op).operation(op, left, right);
    }
}

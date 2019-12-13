package ru.job4j.calculator;


import ru.job4j.calculator.calculators.AbstractCalculator;
import ru.job4j.calculator.calculators.Calculator;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 09.12.2019.
 */
public class Validator {
    private final static String CHECK_NUMBER = "^[0-9]*[.,]?[0-9]+";
    private Calculator calculator;

    /**
     * Constructor.
     *
     * @param calculator - object extends AbstractCalculator.
     */
    public Validator(AbstractCalculator calculator) {
        this.calculator = calculator;
    }

    /**
     * Check amount.
     *
     * @param number - amount.
     * @return - result.
     */
    private boolean isNumber(String number) {
        return number.matches(CHECK_NUMBER);
    }

    /**
     * Check operator.
     *
     * @param operator - operator.
     * @return - result.
     */
    private boolean isOperator(String operator) {
            return this.calculator.getActions().contains(operator);
    }

    /**
     * Validate line.
     *
     * @param line - line.
     * @return - result.
     */
    public double validate(String[] line) {
        double result = 0;
        if (line.length > 1 && line.length < 4) {
            if (line.length == 2 && isOperator(line[0]) && isNumber(line[1])) {
                    result = this.calculator.operation(line[0], Double.parseDouble(line[1]));
            } else if (line.length == 3 && isNumber(line[0]) && isOperator(line[1]) && isNumber(line[2])) {
                    result = this.calculator.operation(line[1], Double.parseDouble(line[0]), Double.parseDouble(line[2]));
            }
        }
        return result;
    }
}

package ru.job4j.calculator;

import org.junit.Test;
import ru.job4j.calculator.calculators.AbstractCalculator;
import ru.job4j.calculator.calculators.BasicCalculator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class CalculatorTest {
    private AbstractCalculator calc = new BasicCalculator();

    /**
     * Test add.
     */
    @Test
    public void whenAddOnePlusOneThenTwo() {
        this.calc.fillActions();
        double result = calc.operation("+", 1D, 1D);
        double expected = 2D;
        assertThat(result, is(expected));
        result = calc.operation("+", 1D);
        expected = 3D;
        assertThat(result, is(expected));
    }

    /**
     * Test Subtraction.
     */
    @Test
    public void whenAddOneSubtractionOneThenZero() {
        this.calc.fillActions();
        double result = calc.operation("-", 1D, 1D);
        double expected = 0D;
        assertThat(result, is(expected));
        result = calc.operation("-", 1D);
        expected = -1D;
        assertThat(result, is(expected));
    }

    /**
     * Test multiplication.
     */
    @Test
    public void whenAddTwoMultiplicationTwoThenFour() {
        this.calc.fillActions();
        double result = calc.operation("*", 2D, 2D);
        double expected = 4D;
        assertThat(result, is(expected));
        result = calc.operation("*", 2D);
        expected = 8D;
        assertThat(result, is(expected));
    }

    /**
     * Test division.
     */
    @Test
    public void whenAddTwoDivisionTwoThenOne() {
        this.calc.fillActions();
        double result = calc.operation("/", 2D, 2D);
        double expected = 1D;
        assertThat(result, is(expected));
        result = calc.operation("/", 2D);
         expected = 0.5D;
        assertThat(result, is(expected));
    }
}
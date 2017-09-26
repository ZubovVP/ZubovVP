package ru.job4j.calculator;

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
    /**
	* Test add.
	*/
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }
	/**
	* Test add.
	*/
	public void whenAddOneSubtractionOneThenZero() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 0D;
        assertThat(result, is(expected));
	}
	/**
	* Test add.
	*/
	public void whenAddTwoMultiplicationTwoThenFour() {
        Calculator calc = new Calculator();
        calc.add(2D, 2D);
        double result = calc.getResult();
        double expected = 4D;
        assertThat(result, is(expected));
	}
	/**
	* Test add.
	*/
	public void whenAddTwoDivisionTwoThenOne() {
        Calculator calc = new Calculator();
        calc.add(2D, 2D);
        double result = calc.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
	}
}
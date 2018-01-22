package ru.job4j.calculator;

import org.junit.Test;

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
    @Test
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
	@Test
	public void whenAddOneSubtractionOneThenZero() {
        Calculator calc = new Calculator();
        calc.subtract(1D, 1D);
        double result = calc.getResult();
        double expected = 0D;
        assertThat(result, is(expected));
	}
	/**
	* Test add.
	*/
	@Test
	public void whenAddTwoMultiplicationTwoThenFour() {
        Calculator calc = new Calculator();
        calc.multiple(2D, 2D);
        double result = calc.getResult();
        double expected = 4D;
        assertThat(result, is(expected));
	}
	/**
	* Test add.
	*/
	@Test
	public void whenAddTwoDivisionTwoThenOne() {
        Calculator calc = new Calculator();
        calc.div(2D, 2D);
        double result = calc.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
	}
}
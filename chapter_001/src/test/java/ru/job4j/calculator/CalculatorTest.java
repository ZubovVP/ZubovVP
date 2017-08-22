package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }
	public void whenAddOneSubtractionOneThenZero() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 0D;
        assertThat(result, is(expected));
	}
	public void whenAddTwoMultiplicationTwoThenFour() {
        Calculator calc = new Calculator();
        calc.add(2D, 2D);
        double result = calc.getResult();
        double expected = 4D;
        assertThat(result, is(expected));
	}
	public void whenAddTwoDivisionTwoThenOne() {
        Calculator calc = new Calculator();
        calc.add(2D, 2D);
        double result = calc.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
	}
}
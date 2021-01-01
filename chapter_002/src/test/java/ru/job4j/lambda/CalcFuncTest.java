package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 01.01.2021.
 */
public class CalcFuncTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = CalcFunc.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        List<Double> result = CalcFunc.diapason(5, 8, x -> 2 * Math.pow(x, 2) + 2 * x + 3);
        List<Double> expected = Arrays.asList(63D, 87D, 115D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenExponentialFunctionThenExponentialResults() {
        List<Double> result = CalcFunc.diapason(5, 8, x -> Math.pow(x, 3));
        List<Double> expected = Arrays.asList(125D, 216D, 343D);
        assertThat(result, is(expected));
    }
}
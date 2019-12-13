package ru.job4j.calculator.calculators;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.calculator.Validator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 13.12.2019.
 */
public class EngCalculatorTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));

    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void testAddTwoAmount() {
        InputStream is = new ByteArrayInputStream("start\n2 + 1\nNo".getBytes());
        String result = test(is);
        assertThat(result, is(new StringBuilder()
                .append("Please, write for start : ")
                .append("Please, write an example : ")
                .append("Result = 3.0\n")
                .append("Continue? Yes/No").toString()));
    }

    @Test
    public void testMultiplicationAndUseLastResult1() {
        InputStream is = new ByteArrayInputStream("start\n2 * 1\nYes\n* 2\nNo".getBytes());
        String result = test(is);
        assertThat(result, is(new StringBuilder()
                .append("Please, write for start : ")
                .append("Please, write an example : ")
                .append("Result = 2.0\n")
                .append("Continue? Yes/No")
                .append("Please, write an example : ")
                .append("Result = 4.0\n")
                .append("Continue? Yes/No").toString()));
    }

    @Test
    public void testCosine() {
        InputStream is = new ByteArrayInputStream("start\n cos 1\nNo".getBytes());
        String result = test(is);
        assertThat(result, is(new StringBuilder()
                .append("Please, write for start : ")
                .append("Please, write an example : ")
                .append("Result = 0.0\n")
                .append("Continue? Yes/No").toString()));
    }

    private String test(InputStream is) {
        List<AbstractCalculator> simpleCalculators = new ArrayList<>();
        simpleCalculators.add(new BasicCalculator());
        simpleCalculators.add(new LogarithmicCalculator());
        simpleCalculators.add(new ExponentialCalculator());
        simpleCalculators.add(new TrigonometricCalculator());
        AbstractCalculator calculator = new EngCalculator(simpleCalculators);
        calculator.fillActions();
        InteractCalc calc = new InteractCalc(calculator, new Validator(calculator), is);
        calc.calculate();
        return new String(out.toByteArray());
    }
}
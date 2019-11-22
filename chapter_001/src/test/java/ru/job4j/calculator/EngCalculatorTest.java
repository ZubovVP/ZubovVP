package ru.job4j.calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 20.11.2019.
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
    public void testDoNotUseLastResult() {
        InputStream is = new ByteArrayInputStream("0\n+\n1\nNo".getBytes());
        String result = test(is);
        assertThat(result, is(new StringBuilder()
                .append("\nPlease, write first value : \n")
                .append("Please, write operator : \n")
                .append("Please, write second value : \n")
                .append("Your result = 1,000\n")
                .append("Continue? Yse/No : \n")
                .append("Bye!").toString()));
    }

    @Test
    public void testUseLastResult() {
        InputStream is = new ByteArrayInputStream("1\n+\n1\nYes\nYes\n*\n2\nNo".getBytes());
        String result = test(is);
        assertThat(result, is(new StringBuilder()
                .append("\nPlease, write first value : \n")
                .append("Please, write operator : \n")
                .append("Please, write second value : \n")
                .append("Your result = 2,000\n")
                .append("Continue? Yse/No : \n")
                .append("Use last result? (Yes/No)\n")
                .append("Please, write operator : \n")
                .append("Please, write second value : \n")
                .append("Your result = 4,000\n")
                .append("Continue? Yse/No : \n")
                .append("Bye!").toString()));
    }

    @Test
    public void testUseCos() {
        String pi = String.valueOf(Math.PI).replace(".", ",");
        InputStream is = new ByteArrayInputStream(new StringBuilder()
                .append("0,11111111111111\n")
                .append("cos\n")
                .append(pi)
                .append("\n")
                .append("No\n").toString()
                .getBytes());
        test(is, "-1,000");
    }

    @Test
    public void testUseCtg() {
        String count = String.valueOf(5 * Math.PI / 4).replace(".", ",");
        InputStream is = new ByteArrayInputStream(new StringBuilder()
                .append("0,11111111111111\n")
                .append("ctg\n")
                .append(count)
                .append("\n")
                .append("No\n").toString()
                .getBytes());
        test(is, "1,000");
    }

    @Test
    public void testUseSinh() {
        String count = String.valueOf(Math.PI / 4).replace(".", ",");
        InputStream is = new ByteArrayInputStream(new StringBuilder()
                .append("0,11111111111111\n")
                .append("sinh\n")
                .append(count)
                .append("\n")
                .append("No\n").toString()
                .getBytes());
        test(is, "0,869");
    }

    private String test(InputStream is) {
        InteractCalc c = new InteractCalc(new EngCalculator(), is, this.stdout);
        c.calculate();
        return new String(out.toByteArray());
    }

    private void test(InputStream is, String result) {
        InteractCalc c = new InteractCalc(new EngCalculator(), is, this.stdout);
        c.calculate();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("\nPlease, write first value : \n")
                .append("Please, write operator : \n")
                .append("Please, write second value : \n")
                .append("Your result = ")
                .append(result)
                .append("\n")
                .append("Continue? Yse/No : \n")
                .append("Bye!").toString()));
    }
}
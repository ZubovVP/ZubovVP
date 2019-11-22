package ru.job4j.calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 08.11.2019.
 */
public class InteractCalcTest {
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
        InputStream is = new ByteArrayInputStream("1\n+\n1\nNo".getBytes());
        String result = test(is);
        assertThat(result, is(new StringBuilder()
                .append("\nPlease, write first value : \n")
                .append("Please, write operator : \n")
                .append("Please, write second value : \n")
                .append("Your result = 2,000\n")
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

    private String test(InputStream is) {
        InteractCalc c = new InteractCalc(new Calculator(), is, this.stdout);
        c.calculate();
        return new String(out.toByteArray());
    }
}
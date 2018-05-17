package ru.job4j.trade;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Add tests.
 *
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class TradingTest {
    private Trading tr = new Trading();

    @Before
    public void start() {
    tr.takeApplication(new Application("Gas", "add", "buy", 10, 45));
}
    @Test
    public void whenAddSecondApplicationWithSamePriceAndType() {
        tr.takeApplication(new Application("Gas", "add", "buy", 10, 16));
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        tr.getTable();
        assertThat(new String(out.toByteArray()), is(new StringJoiner(System.getProperty("line.separator"), "", "\n")
        .add("Gas")
        .add("Buy - Price")
        .add(61 + " - " + 10.0)
        .add("---------------------").toString()));
    }

    @Test
    public  void whedAddSecondApplicationWithDifferentPrice() {
        Application testAp3 = new Application("Gas", "add", "buy", 20, 16);
        tr.takeApplication(testAp3);
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        tr.getTable();
        assertThat(new String(out.toByteArray()), is(new StringJoiner(System.getProperty("line.separator"), "", "\n")
                .add("Gas")
                        .add("Buy - Price")
                        .add(16 + " - " + 20.0)
                        .add("---------------------")
                .add("Gas")
                        .add("Buy - Price")
                        .add(45 + " - " + 10.0)
                        .add("---------------------").toString()));
    }

    @Test
    public  void whedAddSecondApplicationWithDifferentType() {
        Application testAp3 = new Application("Prom", "add", "buy", 20, 16);
        tr.takeApplication(testAp3);
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        tr.getTable();
        assertThat(new String(out.toByteArray()), is(new StringJoiner(System.getProperty("line.separator"), "", "\n")
                .add("Prom")
                        .add("Buy - Price")
                        .add(16 + " - " + 20.0)
                        .add("---------------------").toString() + (new StringJoiner(System.getProperty("line.separator"), "", "\n")
                .add("Gas")
                        .add("Buy - Price")
                        .add(45 + " - " + 10.0)
                        .add("---------------------").toString())));
    }

    @Test
    public void whenAddSecondApplicationWithAlterativeActiion() {
        Application testAp3 = new Application("Gas", "add", "sell", 10, 40);
        tr.takeApplication(testAp3);
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        tr.getTable();
        assertThat(new String(out.toByteArray()), is(new StringJoiner(System.getProperty("line.separator"), "", "\n")
                .add("Gas")
                        .add("Buy - Price")
                        .add(5 + " - " + 10.0)
                        .add("---------------------").toString()));
    }

    @Test
    public  void whedAddSecondApplicationWithDifferentPriceAndDeleteIt() {
        Application testAp3 = new Application("Gas", "add", "buy", 20, 16);
        tr.takeApplication(testAp3);
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        tr.getTable();
        assertThat(new String(out.toByteArray()), is(new StringJoiner(System.getProperty("line.separator"), "", "\n")
                .add("Gas")
                        .add("Buy - Price")
                        .add(16 + " - " + 20.0)
                        .add("---------------------")
                .add("Gas")
                        .add("Buy - Price")
                        .add(45 + " - " + 10.0)
                        .add("---------------------").toString()));

        ByteArrayOutputStream outTwo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outTwo));
        tr.takeApplication(new Application("Gas", "delete", "buy", 20, 16));
        tr.getTable();
        assertThat(new String(outTwo.toByteArray()), is(new StringJoiner(System.getProperty("line.separator"), "", "\n")
                .add("Gas")
                        .add("Buy - Price")
                        .add(45 + " - " + 10.0)
                        .add("---------------------").toString()));
    }

    @Test
    public void testSort() {
        tr.takeApplication(new Application("Gas", "add", "buy", 18, 30));
        tr.takeApplication(new Application("Gas", "add", "buy", 15, 15));
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        tr.getTable();
        assertThat(new String(out.toByteArray()), is(new StringJoiner(System.getProperty("line.separator"), "", "\n")
                .add("Gas")
                        .add("Buy - Price")
                        .add(30 + " - " + 18.0)
                        .add("---------------------")
                .add("Gas")
                        .add("Buy - Price")
                        .add(15 + " - " + 15.0)
                        .add("---------------------")
                .add("Gas")
                        .add("Buy - Price")
                        .add(45 + " - " + 10.0)
                        .add("---------------------").toString()));
    }
}
package ru.job4j.switcher;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ZubovVP on 11.09.2018
 * zubovvp@yadndex.ru
 */
public class SwitcherTest {
    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    private Switcher sw = new Switcher();


    @Test
    public void testPlayShouldTrue() throws InterruptedException {
        System.setOut(new PrintStream(this.out));
        sw.play();
        Thread.sleep(410);
        String result = sw.getLine();
        String first = result.substring(0, 10);
        String second = result.substring(10);
        assertThat(sw.getLine(), is(new StringBuilder().
                append(first).append(second).
                toString()));
        Thread.sleep(210);
        assertThat(sw.getLine(), is(new StringBuilder().
                append(first).append(second).append(first)
                .toString()));
    }

    @Test
    public void addNumberShouldNumber() {
        assertThat(sw.getLine(), is(""));
        sw.add(1);
        assertThat(sw.getLine(), is("1"));
        sw.add(2);
        assertThat(sw.getLine(), is("12"));
    }

    @Test
    public void testShutdown() throws InterruptedException {
        sw.play();
        Thread.sleep(100);
        sw.shutdown();
        Thread.sleep(210);
        String res = sw.getLine();
        String result2 = sw.getLine();
        assertThat(res, is(result2));
    }
}
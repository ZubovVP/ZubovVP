package ru.job4j.ex;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 17.12.2020.
 */
public class FactTest {
    private Fact fact = new Fact();

    @Test(expected = IllegalArgumentException.class)
    public void whenElemnetIsMinus3ThenException() {
        fact.calc(-3);
    }

    @Test
    public void whenElemnetIsZeroThenResultIsOne() {
        Assert.assertThat(1, is(this.fact.calc(0)));
    }

    @Test
    public void whenElemnetIsFiveThenResultIsO120() {
        Assert.assertThat(120, is(this.fact.calc(5)));
    }

}
package ru.job4j.condition;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 29.11.2020.
 */
public class DistanceTest {

    @Test
    public void testDistance() {
        Assert.assertThat(new Distance().distance(0, 0, 2, 0), is(2.0));
    }

    @Test
    public void testDistance2() {
        Assert.assertThat(new Distance().distance(0, 0, 0, 0), is(0.0));
    }
}
package ru.job4j.pool;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static ru.job4j.pool.RolColSum.asyncSum;
import static ru.job4j.pool.RolColSum.sum;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 26.02.2021.
 */
public class RolColSumTest {
    private int[][] text1 = {{1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };

    @Test
    public void testsum() {
        RolColSum.Sums[] result = sum(this.text1);
        Assert.assertThat(result[0].getColSum(), is(12));
        Assert.assertThat(result[0].getRowSum(), is(6));
        Assert.assertThat(result[1].getColSum(), is(15));
        Assert.assertThat(result[1].getRowSum(), is(15));
    }

    @Test
    public void testAsyncSum() {
        RolColSum.Sums[] result = asyncSum(this.text1);
        Assert.assertThat(result[0].getColSum(), is(12));
        Assert.assertThat(result[0].getRowSum(), is(6));
        Assert.assertThat(result[1].getColSum(), is(15));
        Assert.assertThat(result[1].getRowSum(), is(15));
    }
}
package ru.job4j.pool;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 23.02.2021.
 */
public class FinderTest {
    @Test
    public void testFinderIndex() {
        int[] aaa = new int[]{1, 6, 6, 7, 12, 647, 77, 12, 0, 7, 841, 546, 987412, 1324, 48574, 1274, 4121, 74984, 3246, 47, 1, 4, 749842, 12, 15, 777, 165498, 121574, 12574, 1245, 1324, 14174, 13241654, 12346, 12549674, 1234174, 1654, 152485, 418574, 0};
        Finder finder = new Finder(aaa);
        Assert.assertThat(finder.findIndex(777), is(25));
    }
}
package ru.job4j.list;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 15.01.2021.
 */
public class ListUtilsTest {
    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenDeleteFromListUseAnotherList() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.removeAll(list, elements);
        assertThat(Collections.singletonList(2), Is.is(list));
    }

    @Test
    public void whenDeleteUsePredicate() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeIf(list, new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 2;
            }
        });
        assertThat(Arrays.asList(1, 2), Is.is(list));
    }

    @Test
    public void whenReplaceUsePredicate() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.replaceIf(list, new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer <= 2;
            }
        }, 3);
        assertThat(Arrays.asList(3, 3, 3), Is.is(list));
    }

}
package ru.job4j.collection;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 24.12.2020.
 */
public class LexSortTest {
    @Test
    public void sortNum1and2and10() {
        String[] input = {
                "10. Task.",
                "1. Task.",
                "2. Task."
        };
        String[] out = {
                "1. Task.",
                "2. Task.",
                "10. Task."
        };
        Arrays.sort(input, new LexSort());
        assertThat(input, is(out));
    }

    @Test
    public void sortNum() {
        String[] input = {
                "10.1.2. Task.",
                "1.1.1. Task.",
                "2. Task."
        };
        String[] out = {
                "1.1.1. Task.",
                "2. Task.",
                "10.1.2. Task."
        };
        Arrays.sort(input, new LexSort());
        assertThat(input, is(out));
    }

    @Test
    public void sortNum2() {
        String[] input = {
                "10.1.2. Task.",
                "10.1. Task.",
                "2. Task."
        };
        String[] out = {
                "2. Task.",
                "10.1. Task.",
                "10.1.2. Task."
        };
        Arrays.sort(input, new LexSort());
        assertThat(input, is(out));
    }

    @Test
    public void sortNum3() {
        String[] input = {
                "10.1.2. Tasks.",
                "10.1.2. Task.",
                "2. Task."
        };
        String[] out = {
                "2. Task.",
                "10.1.2. Task.",
                "10.1.2. Tasks."
        };
        Arrays.sort(input, new LexSort());
        assertThat(input, is(out));
    }
}
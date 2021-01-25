package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 25.01.2021.
 */
public class ArgsNameTest {
    @Test
    public void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test
    public void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[]{"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[]{});
        jvm.get("Xmx");
    }
}
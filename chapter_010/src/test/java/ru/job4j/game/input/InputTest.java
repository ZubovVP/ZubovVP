package ru.job4j.game.input;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 04.02.2020.
 */
public class InputTest {

    @Test
    public void testReadLine() {
        String line = "Test\nTest2";
        InputStream is = new ByteArrayInputStream(line.getBytes());
        System.setIn(is);
        Input input = new Input();
        String result = input.read();
        assertThat(result, is("Test"));
        result = input.read();
        assertThat(result, is("Test2"));
    }
}
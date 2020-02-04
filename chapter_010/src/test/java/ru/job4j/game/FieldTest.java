package ru.job4j.game;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 14.01.2020.
 */
public class FieldTest {
    private Field field = new Field();

    @Test
    public void testAddSymbolOnAField() {
        assertNull(this.field.getField()[1][1]);
        assertTrue(this.field.add(1, 1, 'x'));
        assertThat(this.field.getField()[1][1], is('x'));
    }

    @Test
    public void testClearAll() {
        this.field.add(1, 1, 'x');
        this.field.clearAll();
        assertNull(this.field.getField()[1][1]);
    }

    @Test
    public void testAddSymbolOnABusyPlace() {
        this.field.add(1, 1, 'x');
        assertFalse(this.field.add(1, 1, 'o'));
        assertThat(this.field.getField()[1][1], is('x'));
    }

}
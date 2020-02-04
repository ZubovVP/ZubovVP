package ru.job4j.game.check;

import org.junit.Test;
import ru.job4j.game.Field;

import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 04.02.2020.
 */
public class CheckCoordinateTest {

    @Test
    public void testCorrectCoordinateShouldTrue() {
        Field field = new Field(3);
        CheckCoordinate check = new CheckCoordinate();
        assertTrue(check.check(field, 1, 1));
    }

    @Test
    public void testWrongCoordinateShouldFalse() {
        Field field = new Field(3);
        CheckCoordinate check = new CheckCoordinate();
        assertFalse(check.check(field, 3, 3));
        assertFalse(check.check(field, -1, -1));
        assertFalse(check.check(field, 100, 0));
        assertFalse(check.check(field, 0, 100));
    }


}
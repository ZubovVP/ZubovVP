package ru.job4j.game.check;

import org.junit.Test;
import ru.job4j.game.Field;
import ru.job4j.game.check.CheckWinThreeMatches;

import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 24.01.2020.
 */
public class CheckWinThreeMatchesTest {

    @Test
    public void testDiagonalShouldTrue() {
        Field field = new Field(3);
        CheckWinThreeMatches cheker = new CheckWinThreeMatches(field);
        field.add(0, 0, 'x');
        assertFalse(cheker.checkWin('x'));
        field.add(1, 1, 'x');
        assertFalse(cheker.checkWin('x'));
        field.add(2, 2, 'x');
        assertTrue(cheker.checkWin('x'));
    }

    @Test
    public void testAnotherSymbolShouldFalse() {
        Field field = new Field(3);
        CheckWinThreeMatches cheker = new CheckWinThreeMatches(field);
        field.add(0, 0, 'x');
        field.add(1, 0, 'x');
        field.add(2, 0, 'x');
        assertFalse(cheker.checkWin('o'));
        assertTrue(cheker.checkWin('x'));
    }

    @Test
    public void testUprightShouldTrue() {
        Field field = new Field(3);
        CheckWinThreeMatches cheker = new CheckWinThreeMatches(field);
        field.add(1, 0, 'x');
        field.add(1, 1, 'x');
        field.add(1, 2, 'x');
        assertFalse(cheker.checkWin('o'));
        assertTrue(cheker.checkWin('x'));
    }

    @Test
    public void testHorizontallyShouldTrue() {
        Field field = new Field(3);
        CheckWinThreeMatches cheker = new CheckWinThreeMatches(field);
        field.add(0, 0, 'x');
        assertFalse(cheker.checkWin('x'));
        field.add(1, 1, 'x');
        assertFalse(cheker.checkWin('x'));
        field.add(2, 2, 'x');
        assertTrue(cheker.checkWin('x'));
        assertFalse(cheker.checkWin('o'));
    }
}
package ru.job4j.game.check;

import org.junit.Test;
import ru.job4j.game.Field;
import ru.job4j.game.check.CheckWinFourMatches;

import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 24.01.2020.
 */
public class CheckWinFourMatchesTest {

    @Test
    public void testDiagonalShouldTrue() {
        Field field = new Field(9);
        ru.job4j.game.check.CheckWinFourMatches cheker = new ru.job4j.game.check.CheckWinFourMatches(field);
        field.add(8, 8, 'x');
        assertFalse(cheker.checkWin('x'));
        field.add(7, 7, 'x');
        assertFalse(cheker.checkWin('x'));
        field.add(6, 6, 'x');
        assertFalse(cheker.checkWin('x'));
        field.add(5, 5, 'x');
        assertTrue(cheker.checkWin('x'));
    }

    @Test
    public void testAnotherSymbolShouldFalse() {
        Field field = new Field(9);
        ru.job4j.game.check.CheckWinFourMatches cheker = new ru.job4j.game.check.CheckWinFourMatches(field);
        field.add(0, 0, 'x');
        field.add(1, 0, 'x');
        field.add(2, 0, 'x');
        field.add(3, 0, 'x');
        assertFalse(cheker.checkWin('o'));
    }

    @Test
    public void testUprightShouldTrue() {
        Field field = new Field(9);
        ru.job4j.game.check.CheckWinFourMatches cheker = new ru.job4j.game.check.CheckWinFourMatches(field);
        field.add(1, 0, 'x');
        field.add(1, 1, 'x');
        field.add(1, 2, 'x');
        field.add(1, 3, 'x');
        assertTrue(cheker.checkWin('x'));
    }

    @Test
    public void testHorizontallyShouldTrue() {
        Field field = new Field(9);
        ru.job4j.game.check.CheckWinFourMatches cheker = new ru.job4j.game.check.CheckWinFourMatches(field);
        field.add(0, 0, 'x');
        assertFalse(cheker.checkWin('x'));
        field.add(1, 1, 'x');
        assertFalse(cheker.checkWin('x'));
        field.add(2, 2, 'x');
        assertFalse(cheker.checkWin('x'));
        field.add(3, 3, 'x');
        assertTrue(cheker.checkWin('x'));
        assertFalse(cheker.checkWin('o'));
    }
}
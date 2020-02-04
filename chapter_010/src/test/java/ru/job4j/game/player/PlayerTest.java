package ru.job4j.game.player;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.game.Field;
import ru.job4j.game.check.CheckCoordinate;

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
public class PlayerTest {

    @Test
    public void testMovePlayer() {
        Field field = new Field(3);
        assertThat(field.getSymbol(1, 1), is(' '));
        InputStream is = new ByteArrayInputStream("1,1".getBytes());
        System.setIn(is);
        try (Player player = new Player("Name", 'X', new CheckCoordinate())) {
            player.move(field);
        }
        assertThat(field.getSymbol(1, 1), is('X'));
    }
}
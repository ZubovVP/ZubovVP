package ru.job4j.game.player;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.game.Field;
import ru.job4j.game.check.CheckWin;
import ru.job4j.game.check.CheckWinThreeMatches;

import static org.junit.Assert.assertTrue;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 26.01.2020.
 */
public class BotFourMatchesTest {
    @Test
    public void checkMove() {
        boolean result = false;
        Field field = new Field(3);
        AbstractBot bot = new BotFourMatches("Name", 'X');
        bot.move(field);
        for (int x = 0; x < field.getSize(); x++) {
            if (!result) {
                for (int y = 0; y < field.getSize(); y++) {
                    if (field.getSymbol(x, y) == 'X') {
                        result = true;
                        break;
                    }
                }
            } else {
                break;
            }
        }
        assertTrue(result);
    }

    @Test
    public void checkRandomMove() {
        boolean result = false;
        Field field = new Field(3);
        AbstractBot bot = new BotFourMatches("Name", 'X');
        bot.randomMove(field);
        for (int x = 0; x < field.getSize(); x++) {
            if (!result) {
                for (int y = 0; y < field.getSize(); y++) {
                    if (field.getSymbol(x, y) == bot.getOwnSymbol()) {
                        result = true;
                        break;
                    }
                }
            } else {
                break;
            }
        }
        assertTrue(result);
    }

    @Ignore
    public void testWinFor4Matches() {
        Field field = new Field(3);
        AbstractBot bot = new BotFourMatches("Name", 'X');
        bot.move(field);
        bot.move(field);
        bot.move(field);
        bot.move(field);
        bot.move(field);
        bot.move(field);
        CheckWin check = new CheckWinThreeMatches(field);
        assertTrue(check.checkWin(bot.getOwnSymbol()));
    }
}
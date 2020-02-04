package ru.job4j.game.check;

import ru.job4j.game.Field;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 02.02.2020.
 */
public class CheckCoordinate {

    /**
     * Check coordinate for Player.
     *
     * @param field - field.
     * @param x - x coordinate.
     * @param y - y coordinate.
     * @return - result.
     */
    public boolean check(Field field, int x, int y) {
        boolean result = false;
        if (field.getSize() > x && x >= 0 && field.getSize() > y && y >= 0) {
            if (field.getSymbol(x, y) == ' ') {
                result = true;
            }
        }
        return result;
    }
}

package ru.job4j.game.check;

import ru.job4j.game.Field;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 02.02.2020.
 */
public abstract class CheckWin {
    protected Field field;

    public CheckWin(Field field) {
        this.field = field;
    }

    public abstract boolean checkWin(Character symbol);
}

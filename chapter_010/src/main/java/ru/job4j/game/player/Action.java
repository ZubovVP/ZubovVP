package ru.job4j.game.player;

import ru.job4j.game.Field;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 30.01.2020.
 */
public interface Action {
    boolean defense(Field field);

    boolean attack(Field field);
}
